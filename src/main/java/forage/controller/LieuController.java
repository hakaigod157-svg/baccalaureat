package forage.controller;

import forage.model.Lieu;
import forage.service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lieu")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("lieu/liste");
        mav.addObject("lieus", lieuService.getAllLieus());
        mav.addObject("titre", "Liste des Lieux");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("lieu/form");
        mav.addObject("lieu", new Lieu());
        mav.addObject("titre", "Nouveau Lieu");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Lieu lieu) {
        lieuService.saveLieu(lieu);
        return "redirect:/lieu/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("lieu/form");
        mav.addObject("lieu", lieuService.getLieuById(id));
        mav.addObject("titre", "Modifier Lieu");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        lieuService.deleteLieuById(id);
        return "redirect:/lieu/liste";
    }
}
