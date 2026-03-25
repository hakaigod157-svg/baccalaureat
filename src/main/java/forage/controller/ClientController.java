package forage.controller;

import forage.model.Client;
import forage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/liste")
    public ModelAndView liste() {
        ModelAndView mav = new ModelAndView("client/liste");
        mav.addObject("clients", clientService.getAllClients());
        mav.addObject("titre", "Liste des Clients");
        return mav;
    }

    @GetMapping("/nouveau")
    public ModelAndView nouveau() {
        ModelAndView mav = new ModelAndView("client/form");
        mav.addObject("client", new Client());
        mav.addObject("titre", "Nouveau Client");
        return mav;
    }

    @PostMapping("/sauvegarder")
    public String sauvegarder(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/client/liste";
    }

    @GetMapping("/modifier/{id}")
    public ModelAndView modifier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("client/form");
        mav.addObject("client", clientService.getClientById(id));
        mav.addObject("titre", "Modifier Client");
        return mav;
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Integer id) {
        clientService.deleteClientById(id);
        return "redirect:/client/liste";
    }
}
