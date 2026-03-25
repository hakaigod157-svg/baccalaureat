package forage.controller;

import forage.model.Demande;
import forage.model.Client;
import forage.model.Lieu;
import forage.service.DemandeService;
import forage.service.ClientService;
import forage.service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;

@Controller
@RequestMapping("/demande")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LieuService lieuService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Client.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(clientService.getClientById(Integer.parseInt(text)));
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });

        binder.registerCustomEditor(Lieu.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(lieuService.getLieuById(Integer.parseInt(text)));
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });
    }

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("demande/liste");
        mav.addObject("demandes", demandeService.getAllDemandes());
        mav.addObject("titre", "Liste des Demandes");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("demande/form");
        mav.addObject("demande", new Demande());
        mav.addObject("clientsList", clientService.getAllClients());
        mav.addObject("lieusList", lieuService.getAllLieus());
        mav.addObject("titre", "Nouvelle Demande");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Demande demande) {
        demandeService.saveDemande(demande);
        return "redirect:/demande/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("demande/form");
        mav.addObject("demande", demandeService.getDemandeById(id));
        mav.addObject("clientsList", clientService.getAllClients());
        mav.addObject("lieusList", lieuService.getAllLieus());
        mav.addObject("titre", "Modifier Demande");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        demandeService.deleteDemandeById(id);
        return "redirect:/demande/liste";
    }
}
