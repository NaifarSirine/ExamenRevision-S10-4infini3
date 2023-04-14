package tn.esprit.Services;

import tn.esprit.DAO.Entities.Activite;
import tn.esprit.DAO.Entities.Evenement;
import tn.esprit.DAO.Entities.Utilisateur;

import java.util.List;

public interface IServices {
    Evenement addEvenement(Evenement e);

    Activite addActivite(Activite a);

    void affecterActiviteToEvent(Long activiteId, Long eventId);

    String addUser(Utilisateur u, Long idEvent);

    List<Evenement> getEventsByResponsable(Long responsableId);

    List<Evenement> getEventsOfToday();
}

