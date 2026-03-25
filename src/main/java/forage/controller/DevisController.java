package forage.controller;

import forage.model.Devis;
import forage.model.Demande;
import forage.model.TypeDevis;
import forage.model.Statut;
import forage.service.DevisService;
import forage.service.DemandeService;
import forage.service.TypeDevisService;
import forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;

@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private TypeDevisService typeDevisService;
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
        binder.registerCustomEditor(TypeDevis.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try { setValue(typeDevisService.getTypeDevisById(Integer.parseInt(text))); } catch (Exception e) { setValue(null); }
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
        ModelAndView mav = new ModelAndView("devis/liste");
        mav.addObject("devisList", devisService.getAllDevis());
        mav.addObject("titre", "Liste des Devis");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("devis/form");
        mav.addObject("devis", new Devis());
        mav.addObject("demandesList", demandeService.getAllDemandes());
        mav.addObject("typesList", typeDevisService.getAllTypeDevis());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Nouveau Devis");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Devis devis) {
        devisService.saveDevis(devis);
        return "redirect:/devis/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("devis/form");
        mav.addObject("devis", devisService.getDevisById(id));
        mav.addObject("demandesList", demandeService.getAllDemandes());
        mav.addObject("typesList", typeDevisService.getAllTypeDevis());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Modifier Devis");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        devisService.deleteDevisById(id);
        return "redirect:/devis/liste";
    }
}
