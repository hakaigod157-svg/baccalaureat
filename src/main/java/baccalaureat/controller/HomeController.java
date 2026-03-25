package baccalaureat.controller;

import baccalaureat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired private CandidatService candidatService;
    @Autowired private ProfService profService;
    @Autowired private MatiereService matiereService;
    @Autowired private NoteService noteService;
    @Autowired private SerieService serieService;
    @Autowired private EcoleService ecoleService;
    @Autowired private ParametreService parametreService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("titre", "Tableau de Bord");

        try { mav.addObject("totalCandidats", candidatService.getAllCandidats().size()); }
        catch (Exception e) { mav.addObject("totalCandidats", "—"); }

        try { mav.addObject("totalProfs", profService.getAllProfs().size()); }
        catch (Exception e) { mav.addObject("totalProfs", "—"); }

        try { mav.addObject("totalMatieres", matiereService.getAllMatieres().size()); }
        catch (Exception e) { mav.addObject("totalMatieres", "—"); }

        try { mav.addObject("totalNotes", noteService.countNotes()); }
        catch (Exception e) { mav.addObject("totalNotes", "—"); }

        try { mav.addObject("totalSeries", serieService.getAllSeries().size()); }
        catch (Exception e) { mav.addObject("totalSeries", "—"); }

        try { mav.addObject("totalEcoles", ecoleService.getAllEcoles().size()); }
        catch (Exception e) { mav.addObject("totalEcoles", "—"); }

        try { mav.addObject("totalParametres", parametreService.getAllParametres().size()); }
        catch (Exception e) { mav.addObject("totalParametres", "—"); }

        try { mav.addObject("derniersCandidats", candidatService.getAllCandidats()); }
        catch (Exception e) { mav.addObject("derniersCandidats", new ArrayList<>()); }

        try { mav.addObject("dernieresNotes", noteService.getAllNotes()); }
        catch (Exception e) { mav.addObject("dernieresNotes", new ArrayList<>()); }

        return mav;
    }
}
