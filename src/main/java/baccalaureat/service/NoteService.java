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
        for (Parametre p : parametres) {
            // Vérifier si la matière correspond
            if (p.getMatiere() != null && !p.getMatiere().getIdMatiere().equals(matiereId)) continue;

            String op = p.getOperateur() != null ? p.getOperateur().getOperation() : null;
            String diffStr = p.getDiffNotes();
            if (op == null || diffStr == null) continue;

            double seuil;
            try { seuil = Double.parseDouble(diffStr); } catch (NumberFormatException e) { continue; }

            boolean conditionVerifiee = false;
            switch (op) {
                case "<":  conditionVerifiee = ecart < seuil;  break;
                case ">":  conditionVerifiee = ecart > seuil;  break;
                case "<=": conditionVerifiee = ecart <= seuil; break;
                case ">=": conditionVerifiee = ecart >= seuil; break;
                case "=":  conditionVerifiee = ecart == seuil; break;
            }

            if (conditionVerifiee) {
                String resolution = p.getResolution() != null ? p.getResolution().getOperation().toLowerCase() : "moyenne";
                return appliquerResolution(notes, resolution);
            }
        }

        // Par défaut : moyenne
        return appliquerResolution(notes, "moyenne");
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
        // défaut : moyenne
        double sum = 0;
        for (Note n : notes) sum += n.getValeurNote();
        return sum / notes.size();
    }
}
