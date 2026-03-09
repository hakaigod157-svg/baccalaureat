package baccalaureat.controller;

import baccalaureat.model.Prof;
import baccalaureat.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/prof")
public class ProfController {

    @Autowired
    private ProfService profService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("prof/liste");
        List<Prof> profs = profService.getAllProfs();
        mav.addObject("profs", profs);
        mav.addObject("titre", "Liste des Professeurs");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("prof/form");
        mav.addObject("prof", new Prof());
        mav.addObject("titre", "Nouveau Professeur");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Prof prof) {
        profService.saveProf(prof);
        return "redirect:/prof/liste"; // Redirect back to list
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("prof/form");
        Prof prof = profService.getProfById(id);
        mav.addObject("prof", prof);
        mav.addObject("titre", "Modifier Professeur");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        profService.deleteProfById(id);
        return "redirect:/prof/liste";
    }
}
