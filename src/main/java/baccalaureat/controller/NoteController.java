package baccalaureat.controller;

import baccalaureat.model.Note;
import baccalaureat.service.NoteService;
import baccalaureat.service.CandidatService;
import baccalaureat.service.MatiereService;
import baccalaureat.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import baccalaureat.model.Candidat;
import baccalaureat.model.Matiere;
import baccalaureat.model.Prof;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private CandidatService candidatService;
    
    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private ProfService profService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("note/liste");
        List<Note> notes = noteService.getAllNotes();
        mav.addObject("notes", notes);
        mav.addObject("titre", "Liste des Notes");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("note/form");
        mav.addObject("note", new Note());
        mav.addObject("candidatsList", candidatService.getAllCandidats());
        mav.addObject("matieresList", matiereService.getAllMatieres());
        mav.addObject("profsList", profService.getAllProfs());
        mav.addObject("titre", "Nouvelle Note");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Candidat.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Candidat candidat = candidatService.getCandidatById(id);
                    setValue(candidat);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });

        binder.registerCustomEditor(Matiere.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Matiere matiere = matiereService.getMatiereById(id);
                    setValue(matiere);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });

        binder.registerCustomEditor(Prof.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Prof prof = profService.getProfById(id);
                    setValue(prof);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Note note) {
        noteService.saveNote(note);
        return "redirect:/note/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("note/form");
        Note note = noteService.getNoteById(id);
        mav.addObject("note", note);
        mav.addObject("candidatsList", candidatService.getAllCandidats());
        mav.addObject("matieresList", matiereService.getAllMatieres());
        mav.addObject("profsList", profService.getAllProfs());
        mav.addObject("titre", "Modifier Note");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        noteService.deleteNoteById(id);
        return "redirect:/note/liste";
    }
}
