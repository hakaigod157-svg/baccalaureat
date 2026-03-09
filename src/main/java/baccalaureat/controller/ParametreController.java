package baccalaureat.controller;

import baccalaureat.model.Parametre;
import baccalaureat.service.ParametreService;
import baccalaureat.service.MatiereService;
import baccalaureat.service.OperateurService;
import baccalaureat.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import baccalaureat.model.Matiere;
import baccalaureat.model.Operateur;
import baccalaureat.model.Resolution;

@Controller
@RequestMapping("/parametre")
public class ParametreController {

    @Autowired
    private ParametreService parametreService;
    
    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private OperateurService operateurService;
    
    @Autowired
    private ResolutionService resolutionService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("parametre/liste");
        List<Parametre> parametres = parametreService.getAllParametres();
        mav.addObject("parametres", parametres);
        mav.addObject("titre", "Liste des Paramètres");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("parametre/form");
        mav.addObject("parametre", new Parametre());
        mav.addObject("matieresList", matiereService.getAllMatieres());
        mav.addObject("operateursList", operateurService.getAllOperateurs());
        mav.addObject("resolutionsList", resolutionService.getAllResolutions());
        mav.addObject("titre", "Nouveau Paramètre");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
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

        binder.registerCustomEditor(Operateur.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Operateur operateur = operateurService.getOperateurById(id);
                    setValue(operateur);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });

        binder.registerCustomEditor(Resolution.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Integer id = Integer.parseInt(text);
                    Resolution resolution = resolutionService.getResolutionById(id);
                    setValue(resolution);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Parametre parametre) {
        parametreService.saveParametre(parametre);
        return "redirect:/parametre/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("parametre/form");
        Parametre parametre = parametreService.getParametreById(id);
        mav.addObject("parametre", parametre);
        mav.addObject("matieresList", matiereService.getAllMatieres());
        mav.addObject("operateursList", operateurService.getAllOperateurs());
        mav.addObject("resolutionsList", resolutionService.getAllResolutions());
        mav.addObject("titre", "Modifier Paramètre");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        parametreService.deleteParametreById(id);
        return "redirect:/parametre/liste";
    }
}
