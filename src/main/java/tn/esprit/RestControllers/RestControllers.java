package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.Entities.Activite;
import tn.esprit.DAO.Entities.Evenement;
import tn.esprit.DAO.Entities.Utilisateur;
import tn.esprit.Services.IServices;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestControllers {
    IServices iServices;

    @PostMapping("addEvenement")
    public Evenement addEvenement(@RequestBody Evenement e) {
        return iServices.addEvenement(e);
    }

    @PostMapping("addActivite")
    public Activite addActivite(@RequestBody Activite a) {
        return iServices.addActivite(a);
    }

    @PutMapping("affecterActiviteToEvent")
    public void affecterActiviteToEvent(@RequestParam Long activiteId, @RequestParam Long eventId) {
        iServices.affecterActiviteToEvent(activiteId, eventId);
    }

    @PostMapping("addUser")
    public String addUser(@RequestBody Utilisateur u, @RequestParam Long idEvent) {
        return iServices.addUser(u, idEvent);
    }

    @GetMapping("getEventsByResponsable")
    public List<Evenement> getEventsByResponsable(@RequestParam Long responsableId) {
        return iServices.getEventsByResponsable(responsableId);
    }
}