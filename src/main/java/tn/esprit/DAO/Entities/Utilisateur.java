package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idU;
    String nom;
    String prenom;
    Long numTelephone;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "responsable")
    @JsonIgnore
    List<Evenement> listEResp = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    List<Evenement> listEPar = new ArrayList<>();
}
