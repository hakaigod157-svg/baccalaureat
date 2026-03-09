package baccalaureat.controller;

import baccalaureat.model.*;
import baccalaureat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/recherche")
public class RechercheController {

    @Autowired private NoteService noteService;
    @Autowired private CandidatService candidatService;
    @Autowired private ProfService profService;
    @Autowired private MatiereService matiereService;
    @Autowired private SerieService serieService;
    @Autowired private EcoleService ecoleService;

    @GetMapping("")
    public ModelAndView recherche() {
        ModelAndView mav = new ModelAndView("recherche/index");
        mav.addObject("titre", "Recherche");
        mav.addObject("candidats", candidatService.getAllCandidats());
        mav.addObject("profs", profService.getAllProfs());
        mav.addObject("matieres", matiereService.getAllMatieres());
        mav.addObject("series", serieService.getAllSeries());
        return mav;
    }

    @GetMapping("/candidat/{id}")
    public ModelAndView notesByCandidat(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("recherche/par_candidat");
        Candidat candidat = candidatService.getCandidatById(id);
        List<Note> notes = noteService.getNotesByCandidat(id);
        mav.addObject("titre", candidat != null ? candidat.getNom() + " " + candidat.getPrenom() : "Candidat");
        mav.addObject("candidat", candidat);
        mav.addObject("notes", notes);
        return mav;
    }

    @GetMapping("/prof/{id}")
    public ModelAndView notesByProf(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("recherche/par_prof");
        Prof prof = profService.getProfById(id);
        List<Note> notes = noteService.getNotesByProf(id);
        mav.addObject("titre", prof != null ? "Notes de " + prof.getNom() + " " + prof.getPrenom() : "Professeur");
        mav.addObject("prof", prof);
        mav.addObject("notes", notes);
        return mav;
    }

    @GetMapping("/matiere/{id}")
    public ModelAndView notesByMatiere(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("recherche/par_matiere");
        Matiere matiere = matiereService.getMatiereById(id);
        List<Note> notes = noteService.getNotesByMatiere(id);
        mav.addObject("titre", matiere != null ? "Notes — " + matiere.getNom() : "Matière");
        mav.addObject("matiere", matiere);
        mav.addObject("notes", notes);
        return mav;
    }

    @GetMapping("/serie/{id}")
    public ModelAndView notesBySerie(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("recherche/par_serie");
        Serie serie = serieService.getSerieById(id);
        List<Note> notes = noteService.getNotesBySerie(id);
        mav.addObject("titre", serie != null ? "Série " + serie.getLibelle() : "Série");
        mav.addObject("serie", serie);
        mav.addObject("notes", notes);
        return mav;
    }

    /**
     * Notes finales avec formulaire de filtrage.
     * La première visite (sans paramètres) affiche seulement le formulaire.
     * Après soumission, resultsReady = true et les données sont calculées.
     */
    @GetMapping("/notes-finales")
    public ModelAndView notesFinales(
            @RequestParam(required = false) Integer serieId,
            @RequestParam(required = false) Integer ecoleId,
            @RequestParam(required = false) String mc,
            @RequestParam(required = false) Integer matiereId) {

        ModelAndView mav = new ModelAndView("recherche/notes_finales");
        mav.addObject("titre", "Notes Finales");
        mav.addObject("series", serieService.getAllSeries());
        mav.addObject("ecoles", ecoleService.getAllEcoles());
        mav.addObject("matieresList", matiereService.getAllMatieres());
        mav.addObject("serieId", serieId);
        mav.addObject("ecoleId", ecoleId);
        mav.addObject("mc", mc);
        mav.addObject("matiereId", matiereId);

        boolean submitted = (serieId != null || ecoleId != null || (mc != null && !mc.trim().isEmpty()) || matiereId != null);
        mav.addObject("resultsReady", submitted);

        if (submitted) {
            List<Candidat> candidats = candidatService.getAllCandidats();

            if (serieId != null) {
                candidats = new ArrayList<>();
                for (Candidat c : candidatService.getAllCandidats()) {
                    if (c.getSerie() != null && serieId.equals(c.getSerie().getIdSerie())) candidats.add(c);
                }
            }
            if (ecoleId != null) {
                List<Candidat> filtered = new ArrayList<>();
                for (Candidat c : candidats) {
                    if (c.getEcole() != null && ecoleId.equals(c.getEcole().getIdEcole())) filtered.add(c);
                }
                candidats = filtered;
            }

            if (mc != null && !mc.trim().isEmpty()) {
                String keyword = mc.trim().toLowerCase();
                List<Candidat> filtered = new ArrayList<>();
                for (Candidat c : candidats) {
                    if ((c.getNom() != null && c.getNom().toLowerCase().contains(keyword)) ||
                        (c.getPrenom() != null && c.getPrenom().toLowerCase().contains(keyword)) ||
                        (c.getMatricule() != null && c.getMatricule().toLowerCase().contains(keyword))) {
                        filtered.add(c);
                    }
                }
                candidats = filtered;
            }

            List<Matiere> matieres = matiereService.getAllMatieres();
            if (matiereId != null) {
                List<Matiere> filteredMatieres = new ArrayList<>();
                for (Matiere m : matieres) {
                    if (matiereId.equals(m.getIdMatiere())) {
                        filteredMatieres.add(m);
                    }
                }
                matieres = filteredMatieres;
            }
            Map<Integer, Map<Integer, Double>> notesFinalesMap = new LinkedHashMap<>();
            Map<Integer, Map<Integer, Double>> ecartsMap = new LinkedHashMap<>();

            for (Candidat c : candidats) {
                Map<Integer, Double> nfMap = new LinkedHashMap<>();
                Map<Integer, Double> ecMap = new LinkedHashMap<>();
                for (Matiere m : matieres) {
                    Double nf = noteService.determinerNoteFinale(c.getIdCandidat(), m.getIdMatiere());
                    double ec = noteService.calculerEcart(c.getIdCandidat(), m.getIdMatiere());
                    nfMap.put(m.getIdMatiere(), nf);
                    ecMap.put(m.getIdMatiere(), ec);
                }
                notesFinalesMap.put(c.getIdCandidat(), nfMap);
                ecartsMap.put(c.getIdCandidat(), ecMap);
            }

            mav.addObject("candidats", candidats);
            mav.addObject("matieres", matieres);
            mav.addObject("notesFinalesMap", notesFinalesMap);
            mav.addObject("ecartsMap", ecartsMap);
        }

        return mav;
    }
}
