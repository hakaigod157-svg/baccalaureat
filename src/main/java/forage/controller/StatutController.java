package forage.controller;

import forage.model.Statut;
import forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statut")
public class StatutController {

    @Autowired
    private StatutService statutService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("statut/liste");
        mav.addObject("statuts", statutService.getAllStatuts());
        mav.addObject("titre", "Liste des Statuts");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("statut/form");
        mav.addObject("statut", new Statut());
        mav.addObject("titre", "Nouveau Statut");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Statut statut) {
        statutService.saveStatut(statut);
        return "redirect:/statut/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("statut/form");
        mav.addObject("statut", statutService.getStatutById(id));
        mav.addObject("titre", "Modifier Statut");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        statutService.deleteStatutById(id);
        return "redirect:/statut/liste";
    }
}
