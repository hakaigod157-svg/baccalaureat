package forage.controller;

import forage.model.*;
import forage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private DetailsDevisService detailsDevisService;
    @Autowired
    private PrixTotalDevisService prixTotalDevisService;
    @Autowired
    private DemandeStatutService demandeStatutService;

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
        mav.addObject("titre", "Nouveau Devis");
        return mav;
    }

    @GetMapping("/demande/{id}")
    @ResponseBody
    public Map<String, Object> getDemandeInfo(@PathVariable Integer id) {
        Demande d = demandeService.getDemandeById(id);
        Map<String, Object> info = new HashMap<>();
        if (d != null) {
            info.put("idDemande", d.getIdDemande());
            info.put("clientNom", d.getClient() != null ? d.getClient().getNom() : "");
            info.put("clientPrenom", d.getClient() != null ? d.getClient().getPrenom() : "");
            info.put("dateDemande", d.getDateDemande() != null ? d.getDateDemande().toString() : "");
            info.put("lieu", d.getLieu() != null ? d.getLieu().getLocalisation() + " - " + d.getLieu().getDistrict() : "");
            
            DemandeStatut dernierStatut = demandeStatutService.getStatutDemandeFarany(id);
            if (dernierStatut != null && dernierStatut.getStatut() != null) {
                info.put("etatDemande", dernierStatut.getStatut().getLibelle());
            } else {
                info.put("etatDemande", "Aucun statut");
            }
        }
        return info;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Devis devis, HttpServletRequest request) {
        if (devis.getStatut() == null) {
            devis.setStatut(statutService.getStatutByLibelle("Cree"));
        }
        Devis savedDevis = devisService.saveDevis(devis);

        String[] libelles = request.getParameterValues("detail_libelle");
        String[] prixUnitaires = request.getParameterValues("detail_prixUnitaire");
        String[] quantites = request.getParameterValues("detail_quantite");

        double montantTotal = 0;

        if (libelles != null) {
            for (int i = 0; i < libelles.length; i++) {
                if (libelles[i] == null || libelles[i].trim().isEmpty()) continue;

                DetailsDevis detail = new DetailsDevis();
                detail.setDevis(savedDevis);
                detail.setLibelle(libelles[i]);
                double pu = Double.parseDouble(prixUnitaires[i]);
                int qty = Integer.parseInt(quantites[i]);
                detail.setPrixUnitaire(pu);
                detail.setQuantite(qty);

                detailsDevisService.saveDetailsDevis(detail);
                montantTotal += pu * qty;
            }
        }

        PrixTotalDevis prixTotal = new PrixTotalDevis();
        prixTotal.setDevis(savedDevis);
        prixTotal.setMontantTotal(montantTotal);
        prixTotalDevisService.savePrixTotalDevis(prixTotal);

        if (savedDevis.getTypeDevis() != null) {
            String typeLibelle = savedDevis.getTypeDevis().getLibelle();
            String statutString = "";
            if (typeLibelle != null && typeLibelle.toLowerCase().contains("etude")) {
                statutString = "Devis_Etude_Cree";
            } else if (typeLibelle != null && typeLibelle.toLowerCase().contains("forage")) {
                statutString = "Devis_Forage_Cree";
            }
            
            if (!statutString.isEmpty()) {
                Statut statutPourDemande = statutService.getStatutByLibelle(statutString);
                if (statutPourDemande != null) {
                    DemandeStatut demandeStatut = new DemandeStatut();
                    demandeStatut.setDemande(savedDevis.getDemande());
                    demandeStatut.setDevis(savedDevis);
                    demandeStatut.setStatut(statutPourDemande);
                    demandeStatut.setDateDemandeStatut(java.time.LocalDateTime.now());
                    demandeStatutService.saveDemandeStatut(demandeStatut);
                }
            }
        }

        return "redirect:/devis/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("devis/form");
        mav.addObject("devis", devisService.getDevisById(id));
        mav.addObject("demandesList", demandeService.getAllDemandes());
        mav.addObject("typesList", typeDevisService.getAllTypeDevis());
        mav.addObject("titre", "Modifier Devis");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        devisService.deleteDevisById(id);
        return "redirect:/devis/liste";
    }
}
