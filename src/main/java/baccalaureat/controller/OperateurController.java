package baccalaureat.controller;

import baccalaureat.model.Operateur;
import baccalaureat.service.OperateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/operateur")
public class OperateurController {

    @Autowired
    private OperateurService operateurService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("operateur/liste");
        List<Operateur> operateurs = operateurService.getAllOperateurs();
        mav.addObject("operateurs", operateurs);
        mav.addObject("titre", "Liste des Opérateurs");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("operateur/form");
        mav.addObject("operateur", new Operateur());
        mav.addObject("titre", "Nouvel Opérateur");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Operateur operateur) {
        operateurService.saveOperateur(operateur);
        return "redirect:/operateur/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("operateur/form");
        Operateur operateur = operateurService.getOperateurById(id);
        mav.addObject("operateur", operateur);
        mav.addObject("titre", "Modifier Opérateur");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        operateurService.deleteOperateurById(id);
        return "redirect:/operateur/liste";
    }
}
