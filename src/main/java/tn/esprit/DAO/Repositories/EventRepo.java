package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Evenement;

import java.util.Date;
import java.util.List;

public interface EventRepo extends JpaRepository<Evenement,Long> {
    List<Evenement> findByResponsableIdU(Long idU);
    List<Evenement> findByDateEvent(Date d);
}
