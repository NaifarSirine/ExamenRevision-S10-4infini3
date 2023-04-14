package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //N'est pas obligatoire si l'id est de type String
    @Column(length = 200) //Obligatoire si l'id est de type String
    Long idE;
    String libelle;
    String description;
    @Temporal(TemporalType.DATE)
    Date dateEvent;
    Long nbParticipants;
    @OneToMany(mappedBy = "evenement")
    List<Activite> activites;
    @ManyToOne
    @JsonIgnore
    Utilisateur responsable;
    @ManyToMany(mappedBy = "listEPar")
    @JsonIgnore
    List<Utilisateur> participants;

}
