package forage.controller;

import forage.model.DetailsDevis;
import forage.model.Devis;
import forage.model.Lieu;
import forage.model.Statut;
import forage.service.DetailsDevisService;
import forage.service.DevisService;
import forage.service.LieuService;
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
    private LieuService lieuService;
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
        binder.registerCustomEditor(Lieu.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try { setValue(lieuService.getLieuById(Integer.parseInt(text))); } catch (Exception e) { setValue(null); }
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
        ModelAndView mav = new ModelAndView("detailsdevis/liste");
        mav.addObject("details", detailsDevisService.getAllDetailsDevis());
        mav.addObject("titre", "Détails Devis");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("detailsdevis/form");
        mav.addObject("detail", new DetailsDevis());
        mav.addObject("devisList", devisService.getAllDevis());
        mav.addObject("lieusList", lieuService.getAllLieus());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Nouveau Détail Devis");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute DetailsDevis detail) {
        detailsDevisService.saveDetailsDevis(detail);
        return "redirect:/detailsdevis/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("detailsdevis/form");
        mav.addObject("detail", detailsDevisService.getDetailsDevisById(id));
        mav.addObject("devisList", devisService.getAllDevis());
        mav.addObject("lieusList", lieuService.getAllLieus());
        mav.addObject("statutsList", statutService.getAllStatuts());
        mav.addObject("titre", "Modifier Détail Devis");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        detailsDevisService.deleteDetailsDevisById(id);
        return "redirect:/detailsdevis/liste";
    }
}
