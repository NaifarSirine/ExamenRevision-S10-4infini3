package tn.esprit.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.DAO.Entities.Activite;
import tn.esprit.DAO.Entities.Evenement;
import tn.esprit.DAO.Entities.Role;
import tn.esprit.DAO.Entities.Utilisateur;
import tn.esprit.DAO.Repositories.ActiviteRepo;
import tn.esprit.DAO.Repositories.EventRepo;
import tn.esprit.DAO.Repositories.UserRepo;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class Services implements IServices {
    ActiviteRepo activiteRepo;
    EventRepo eventRepo;
    UserRepo userRepo;

    @Override
    public Evenement addEvenement(Evenement e) {
        return eventRepo.save(e);
    }

    @Override
    public Activite addActivite(Activite a) {
        return activiteRepo.save(a);
    }

    @Override
    public void affecterActiviteToEvent(Long activiteId, Long eventId) {
        Activite a = activiteRepo.findById(activiteId).get(); //parent
        Evenement e = eventRepo.findById(eventId).get(); //child
        a.setEvenement(e);
        activiteRepo.save(a);
    }

    @Override
    public String addUser(Utilisateur u, Long idEvent) {
        String msg = "";
        Evenement e = eventRepo.findById(idEvent).get();
        u = userRepo.save(u);
        if (u.getRole().equals(Role.RESPONSABLE)) {
            if (e.getResponsable() != null) {
                msg = "Il y a déjà un responsable";
            } else {
                //user child et Evenement parent
                e.setResponsable(u);
                eventRepo.save(e);
                msg = "L'affectation du responsable est effectuée avec succès";
                //affectation
            }
        } else if (u.getRole().equals(Role.PARTICIPANT)) {
            if (e.getNbParticipants() > e.getParticipants().size()) {
                //event child et user parent
                u.getListEPar().add(e);
                userRepo.save(u);
                msg = "L'affectation du participant est effectuée avec succès";
                //affectation
            } else {
                msg = "L'évenement est complet";
            }
        }
        return msg;
    }

    @Override
    public List<Evenement> getEventsByResponsable(Long responsableId) {
        return eventRepo.findByResponsableIdU(responsableId);
    }

    @Override
    public List<Evenement> getEventsOfToday() {
        return eventRepo.findByDateEvent(new Date());
    }

    @Scheduled(fixedRate = 30000)
    public void afficher(){
        log.info("********* Evenements d'aujourd'hui *********");
        for (Evenement e:getEventsOfToday()) {
            log.info("Libelle => "+e.getLibelle()+" , Description => "+e.getDescription());
        }
    }
}
