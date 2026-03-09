package baccalaureat.controller;

import baccalaureat.model.Matiere;
import baccalaureat.service.MatiereService;
import baccalaureat.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import baccalaureat.model.Serie;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private SerieService serieService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("matiere/liste");
        List<Matiere> matieres = matiereService.getAllMatieres();
        mav.addObject("matieres", matieres);
        mav.addObject("titre", "Liste des Matières");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("matiere/form");
        mav.addObject("matiere", new Matiere());
        mav.addObject("seriesList", serieService.getAllSeries()); // Needed for dropdown
        mav.addObject("titre", "Nouvelle Matière");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Serie.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Serie serie = serieService.getSerieById(id);
                    setValue(serie);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Matiere matiere) {
        matiereService.saveMatiere(matiere);
        return "redirect:/matiere/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("matiere/form");
        Matiere matiere = matiereService.getMatiereById(id);
        mav.addObject("matiere", matiere);
        mav.addObject("seriesList", serieService.getAllSeries());
        mav.addObject("titre", "Modifier Matière");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        matiereService.deleteMatiereById(id);
        return "redirect:/matiere/liste";
    }
}
