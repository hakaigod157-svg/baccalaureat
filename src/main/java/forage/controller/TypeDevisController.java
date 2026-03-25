package forage.controller;

import forage.model.TypeDevis;
import forage.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/typedevis")
public class TypeDevisController {

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("typedevis/liste");
        mav.addObject("typeDevisList", typeDevisService.getAllTypeDevis());
        mav.addObject("titre", "Liste des Types de Devis");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("typedevis/form");
        mav.addObject("typeDevis", new TypeDevis());
        mav.addObject("titre", "Nouveau Type Devis");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute TypeDevis typeDevis) {
        typeDevisService.saveTypeDevis(typeDevis);
        return "redirect:/typedevis/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("typedevis/form");
        mav.addObject("typeDevis", typeDevisService.getTypeDevisById(id));
        mav.addObject("titre", "Modifier Type Devis");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        typeDevisService.deleteTypeDevisById(id);
        return "redirect:/typedevis/liste";
    }
}
