package baccalaureat.controller;

import baccalaureat.model.Candidat;
import baccalaureat.service.CandidatService;
import baccalaureat.service.EcoleService;
import baccalaureat.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import baccalaureat.model.Ecole;
import baccalaureat.model.Serie;

@Controller
@RequestMapping("/candidat")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;
    
    @Autowired
    private EcoleService ecoleService;
    
    @Autowired
    private SerieService serieService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("candidat/liste");
        List<Candidat> candidats = candidatService.getAllCandidats();
        mav.addObject("candidats", candidats);
        mav.addObject("titre", "Liste des Candidats");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("candidat/form");
        mav.addObject("candidat", new Candidat());
        mav.addObject("ecolesList", ecoleService.getAllEcoles()); // Dropdown
        mav.addObject("seriesList", serieService.getAllSeries()); // Dropdown
        mav.addObject("titre", "Nouveau Candidat");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Ecole.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Ecole ecole = ecoleService.getEcoleById(id);
                    setValue(ecole);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });

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
    public String sauvegarder(@ModelAttribute Candidat candidat) {
        candidatService.saveCandidat(candidat);
        return "redirect:/candidat/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("candidat/form");
        Candidat candidat = candidatService.getCandidatById(id);
        mav.addObject("candidat", candidat);
        mav.addObject("ecolesList", ecoleService.getAllEcoles());
        mav.addObject("seriesList", serieService.getAllSeries());
        mav.addObject("titre", "Modifier Candidat");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        candidatService.deleteCandidatById(id);
        return "redirect:/candidat/liste";
    }
}
