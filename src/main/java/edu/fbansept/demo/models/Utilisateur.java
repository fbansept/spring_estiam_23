package edu.fbansept.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.views.VuePays;
import edu.fbansept.demo.views.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VuePays.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VuePays.class})
    private String prenom;

    @JsonView({VueUtilisateur.class, VuePays.class})
    private String nom;

    @ManyToOne
    @JsonView(VueUtilisateur.class)
    private Pays pays;

    @ManyToMany
    @JsonView(VueUtilisateur.class)
    @JoinTable(
            name = "diplome_utilisateur",
            inverseJoinColumns = @JoinColumn(name = "diplome_id")
    )
    private List<Diplome> listeDiplome = new ArrayList<>();

}
