package forage.controller;

import forage.model.DemandeStatut;
import forage.model.Demande;
import forage.model.Statut;
import forage.service.DemandeStatutService;
import forage.service.DemandeService;
import forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;

@Controller
@RequestMapping("/demandestatut")
public class DemandeStatutController {

    @Autowired
    private DemandeStatutService demandeStatutService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private StatutService statutService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Demande.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try { setValue(demandeService.getDemandeById(Integer.parseInt(text))); } catch (Exception e) { setValue(null); }
            }
        });
        binder.registerCustomEditor(Statut.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try { setValue(statutService.getStatutById(Integer.parseInt(text))); } catch (Exception e) { setValue(null); }
            }
        });
    }

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("demandestatut/liste");
        mav.addObject("demandeStatuts", demandeStatutService.getAllDemandeStatuts());
        mav.addObject("titre", "Statuts des Demandes");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("demandestatut/form");
        mav.addObject("demandeStatut", new DemandeStatut());
        mav.addObject("demandesList", demandeService.getAllDemandes());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Nouveau Statut Demande");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute DemandeStatut ds) {
        demandeStatutService.saveDemandeStatut(ds);
        return "redirect:/demandestatut/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("demandestatut/form");
        mav.addObject("demandeStatut", demandeStatutService.getDemandeStatutById(id));
        mav.addObject("demandesList", demandeService.getAllDemandes());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Modifier Statut Demande");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        demandeStatutService.deleteDemandeStatutById(id);
        return "redirect:/demandestatut/liste";
    }
}
