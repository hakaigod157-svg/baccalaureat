package baccalaureat.service;

import baccalaureat.model.Note;
import baccalaureat.model.Parametre;
import baccalaureat.repository.NoteRepository;
import baccalaureat.repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ParametreRepository parametreRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Integer id) {
        return noteRepository.findById(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNoteById(Integer id) {
        noteRepository.deleteById(id);
    }

    public List<Note> getNotesByCandidat(Integer candidatId) {
        return noteRepository.findByCandidat(candidatId);
    }

    public List<Note> getNotesByMatiere(Integer matiereId) {
        return noteRepository.findByMatiere(matiereId);
    }

    public List<Note> getNotesByProf(Integer profId) {
        return noteRepository.findByProf(profId);
    }

    public List<Note> getNotesBySerie(Integer serieId) {
        return noteRepository.findBySerie(serieId);
    }

    public List<Note> getNotesByCandidatAndMatiere(Integer candidatId, Integer matiereId) {
        return noteRepository.findByCandidatAndMatiere(candidatId, matiereId);
    }

    public long countNotes() {
        return noteRepository.count();
    }

    /**
     * Vérifie si un paramètre s'applique à un écart donné.
     */
    public boolean parametreApplique(double ecart, Parametre p) {
        if (p.getOperateur() == null || p.getDiffNotes() == null) return false;
        String op = p.getOperateur().getOperation();
        double seuil;
        try { seuil = Double.parseDouble(p.getDiffNotes()); } catch (NumberFormatException e) { return false; }

        switch (op) {
            case "<":  return ecart < seuil;
            case ">":  return ecart > seuil;
            case "<=": return ecart <= seuil;
            case ">=": return ecart >= seuil;
        }
        return false;
    }

    /**
     * Vérifie si deux paramètres s'appliquent à l'écart des notes d'un candidat pour une matière.
     */
    public boolean verifierParamDouble(Integer candidatId, Integer matiereId, Parametre p1, Parametre p2) {
        double ecart = calculerEcart(candidatId, matiereId);
        return parametreApplique(ecart, p1) && parametreApplique(ecart, p2);
    }

    /**
     * Compare deux paramètres pour un même écart de notes et retourne celui qui a le seuil le plus proche
     * ou le plus petit en cas d'égalité des distances.
     */
    public Parametre compareSeuil(Integer candidatId, Integer matiereId, Parametre p1, Parametre p2) {
        if (!verifierParamDouble(candidatId, matiereId, p1, p2)) {
            return null;
        }

        double ecartCandidat = calculerEcart(candidatId, matiereId);
        double ecartP1 = 0;
        double ecartP2 = 0;

        try {
            ecartP1 = Double.parseDouble(p1.getDiffNotes());
            ecartP2 = Double.parseDouble(p2.getDiffNotes());
        } catch (NumberFormatException e) {
            return null;
        }

        double diff1 = Math.abs(ecartCandidat - ecartP1);
        double diff2 = Math.abs(ecartCandidat - ecartP2);

        int etatComparaison = 0;
        if (diff1 < diff2) {
            etatComparaison = 1;
        } else if (diff2 < diff1) {
            etatComparaison = 2;
        } else {
            etatComparaison = 3;
        }

        switch (etatComparaison) {
            case 1:
                return p1;
            case 2:
                return p2;
            case 3:
                int etatSeuil = 0;
                boolean isP1PlusPetit = ecartP1 < ecartP2;
                boolean isP2PlusPetit = ecartP2 < ecartP1;
                
                if (isP1PlusPetit) {
                    etatSeuil = 1;
                } else if (isP2PlusPetit) {
                    etatSeuil = 2;
                } else {
                    etatSeuil = 1; 
                }

                switch (etatSeuil) {
                    case 1:
                        return p1;
                    case 2:
                        return p2;
                    default:
                        return p1;
                }
            default:
                return p1;
        }
    }

    /**
     * Calcule l'écart total entre toutes les notes d'un candidat pour une matière donnée.
     * Fait la somme de abs(note_i - note_j) pour toutes les paires (i,j) avec i<j.
     */
    public double calculerEcart(Integer candidatId, Integer matiereId) {
        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidatId, matiereId);
        double ecartTotal = 0.0;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                ecartTotal += Math.abs(notes.get(i).getValeurNote() - notes.get(j).getValeurNote());
            }
        }
        return ecartTotal;
    }

    /**
     * Détermine la note finale d'un candidat pour une matière selon les paramètres.
     * Cherche le premier paramètre dont la condition correspond à l'écart calculé.
     * La résolution peut être "moyenne", "min" ou "max".
     */
    public Double determinerNoteFinale(Integer candidatId, Integer matiereId) {
        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidatId, matiereId);
        if (notes.isEmpty()) return null;
        if (notes.size() == 1) return notes.get(0).getValeurNote();

        double ecart = calculerEcart(candidatId, matiereId);

        List<Parametre> parametres = parametreRepository.findAll();
        List<Parametre> parametresApplicables = new ArrayList<>();

        for (Parametre p : parametres) {
            if (p.getMatiere() != null && !p.getMatiere().getIdMatiere().equals(matiereId)) continue;

            if (parametreApplique(ecart, p)) {
                parametresApplicables.add(p);
            }
        }

        if (parametresApplicables.isEmpty()) {
            return appliquerResolution(notes, "moyenne");
        }

        Parametre parametreChoisi = parametresApplicables.get(0);

        if (parametresApplicables.size() > 1) {
            for (int i = 1; i < parametresApplicables.size(); i++) {
                Parametre pCompare = compareSeuil(candidatId, matiereId, parametreChoisi, parametresApplicables.get(i));
                if (pCompare != null) {
                    parametreChoisi = pCompare;
                }
            }
        }

        String resolution = parametreChoisi.getResolution() != null ? parametreChoisi.getResolution().getOperation().toLowerCase() : "moyenne";
        return appliquerResolution(notes, resolution);
    }

    private Double appliquerResolution(List<Note> notes, String resolution) {
        if (notes.isEmpty()) return null;
        if (resolution.contains("moy") || resolution.contains("average")) {
            double sum = 0;
            for (Note n : notes) sum += n.getValeurNote();
            return sum / notes.size();
        } else if (resolution.contains("min") || resolution.contains("basse") || resolution.contains("faible")) {
            return notes.stream().mapToDouble(Note::getValeurNote).min().orElse(0);
        } else if (resolution.contains("max") || resolution.contains("haute") || resolution.contains("plus")) {
            return notes.stream().mapToDouble(Note::getValeurNote).max().orElse(0);
        }
        double sum = 0;
        for (Note n : notes) sum += n.getValeurNote();
        return sum / notes.size();
    }
}
