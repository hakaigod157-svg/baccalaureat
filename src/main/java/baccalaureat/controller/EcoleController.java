package baccalaureat.controller;

import baccalaureat.model.Ecole;
import baccalaureat.service.EcoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ecole")
public class EcoleController {

    @Autowired
    private EcoleService ecoleService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("ecole/liste");
        List<Ecole> ecoles = ecoleService.getAllEcoles();
        mav.addObject("ecoles", ecoles);
        mav.addObject("titre", "Liste des Ecoles");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("ecole/form");
        mav.addObject("ecole", new Ecole());
        mav.addObject("titre", "Nouvelle Ecole");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Ecole ecole) {
        ecoleService.saveEcole(ecole);
        return "redirect:/ecole/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("ecole/form");
        Ecole ecole = ecoleService.getEcoleById(id);
        mav.addObject("ecole", ecole);
        mav.addObject("titre", "Modifier Ecole");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        ecoleService.deleteEcoleById(id);
        return "redirect:/ecole/liste";
    }
}
