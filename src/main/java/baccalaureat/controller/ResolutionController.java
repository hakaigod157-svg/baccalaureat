package baccalaureat.controller;

import baccalaureat.model.Resolution;
import baccalaureat.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/resolution")
public class ResolutionController {

    @Autowired
    private ResolutionService resolutionService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("resolution/liste");
        List<Resolution> resolutions = resolutionService.getAllResolutions();
        mav.addObject("resolutions", resolutions);
        mav.addObject("titre", "Liste des Résolutions");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("resolution/form");
        mav.addObject("resolution", new Resolution());
        mav.addObject("titre", "Nouvelle Résolution");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Resolution resolution) {
        resolutionService.saveResolution(resolution);
        return "redirect:/resolution/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("resolution/form");
        Resolution resolution = resolutionService.getResolutionById(id);
        mav.addObject("resolution", resolution);
        mav.addObject("titre", "Modifier Résolution");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        resolutionService.deleteResolutionById(id);
        return "redirect:/resolution/liste";
    }
}
