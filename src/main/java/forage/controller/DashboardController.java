package forage.controller;

import forage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class DashboardController {

    @Autowired private ClientService clientService;
    @Autowired private DemandeService demandeService;
    @Autowired private DevisService devisService;
    @Autowired private LieuService lieuService;
    @Autowired private StatutService statutService;
    @Autowired private TypeDevisService typeDevisService;
    @Autowired private DemandeStatutService demandeStatutService;
    @Autowired private DetailsDevisService detailsDevisService;

    @GetMapping("/")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("forage/dashboard");
        mav.addObject("titre", "Tableau de Bord — Forage");

        try { mav.addObject("totalClients", clientService.countClients()); }
        catch (Exception e) { mav.addObject("totalClients", "—"); }

        try { mav.addObject("totalDemandes", demandeService.countDemandes()); }
        catch (Exception e) { mav.addObject("totalDemandes", "—"); }

        try { mav.addObject("totalDevis", devisService.countDevis()); }
        catch (Exception e) { mav.addObject("totalDevis", "—"); }

        try { mav.addObject("totalLieux", lieuService.countLieux()); }
        catch (Exception e) { mav.addObject("totalLieux", "—"); }

        try { mav.addObject("totalStatuts", statutService.countStatuts()); }
        catch (Exception e) { mav.addObject("totalStatuts", "—"); }

        try { mav.addObject("totalTypeDevis", typeDevisService.countTypeDevis()); }
        catch (Exception e) { mav.addObject("totalTypeDevis", "—"); }

        try { mav.addObject("totalDemandeStatuts", demandeStatutService.countDemandeStatuts()); }
        catch (Exception e) { mav.addObject("totalDemandeStatuts", "—"); }

        try { mav.addObject("totalDetailsDevis", detailsDevisService.countDetailsDevis()); }
        catch (Exception e) { mav.addObject("totalDetailsDevis", "—"); }

        try { mav.addObject("dernieresDemandes", demandeService.getRecentDemandes(7)); }
        catch (Exception e) { mav.addObject("dernieresDemandes", new ArrayList<>()); }

        try { mav.addObject("derniersDevis", devisService.getRecentDevis(7)); }
        catch (Exception e) { mav.addObject("derniersDevis", new ArrayList<>()); }

        return mav;
    }
}
