package baccalaureat.controller;

import baccalaureat.model.Serie;
import baccalaureat.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("serie/liste");
        List<Serie> series = serieService.getAllSeries();
        mav.addObject("series", series);
        mav.addObject("titre", "Liste des Séries");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("serie/form");
        mav.addObject("serie", new Serie());
        mav.addObject("titre", "Nouvelle Série");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Serie serie) {
        serieService.saveSerie(serie);
        return "redirect:/serie/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("serie/form");
        Serie serie = serieService.getSerieById(id);
        mav.addObject("serie", serie);
        mav.addObject("titre", "Modifier Série");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        serieService.deleteSerieById(id);
        return "redirect:/serie/liste";
    }
}
