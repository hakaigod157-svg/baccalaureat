package forage.controller;

import forage.model.DetailsDevis;
import forage.model.Devis;
import forage.model.Statut;
import forage.service.DetailsDevisService;
import forage.service.DevisService;
import forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;

@Controller
@RequestMapping("/detailsdevis")
public class DetailsDevisController {

    @Autowired
    private DetailsDevisService detailsDevisService;
    @Autowired
    private DevisService devisService;
    @Autowired
    private StatutService statutService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Devis.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try { setValue(devisService.getDevisById(Integer.parseInt(text))); } catch (Exception e) { setValue(null); }
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
        ModelAndView mav = new ModelAndView("detailsdevis/choix");
        mav.addObject("devisList", devisService.getAllDevis());
        mav.addObject("titre", "Choisir un Devis");
        return mav;
    }

    @GetMapping("/devis/{idDevis}")
    public ModelAndView detailsParDevis(@PathVariable Integer idDevis) {
        ModelAndView mav = new ModelAndView("detailsdevis/liste");
        Devis devis = devisService.getDevisById(idDevis);
        mav.addObject("devis", devis);
        mav.addObject("details", detailsDevisService.getDetailsDevisByDevisId(idDevis));
        mav.addObject("titre", "Détails du Devis N°" + idDevis);
        return mav;
    }

    @GetMapping("/devis/{idDevis}/nouveau")
    public ModelAndView nouveau(@PathVariable Integer idDevis) {
        ModelAndView mav = new ModelAndView("detailsdevis/form");
        DetailsDevis detail = new DetailsDevis();
        detail.setDevis(devisService.getDevisById(idDevis));
        mav.addObject("detail", detail);
        mav.addObject("idDevis", idDevis);
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Nouveau Détail — Devis N°" + idDevis);
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute DetailsDevis detail) {
        detailsDevisService.saveDetailsDevis(detail);
        Integer idDevis = detail.getDevis().getIdDevis();
        return "redirect:/detailsdevis/devis/" + idDevis;
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("detailsdevis/form");
        DetailsDevis detail = detailsDevisService.getDetailsDevisById(id);
        Integer idDevis = detail.getDevis().getIdDevis();
        mav.addObject("detail", detail);
        mav.addObject("idDevis", idDevis);
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Modifier Détail — Devis N°" + idDevis);
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        DetailsDevis detail = detailsDevisService.getDetailsDevisById(id);
        Integer idDevis = detail.getDevis().getIdDevis();
        detailsDevisService.deleteDetailsDevisById(id);
        return "redirect:/detailsdevis/devis/" + idDevis;
    }
}
