package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Utilisateur;

public interface UserRepo extends JpaRepository<Utilisateur,Long> {
}
