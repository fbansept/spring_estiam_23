package edu.fbansept.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.views.VuePays;
import edu.fbansept.demo.views.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VuePays.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VuePays.class})
    private String nom;

    @OneToMany(mappedBy = "pays")
    @JsonView(VuePays.class)
    private List<Utilisateur> utilisateurs;


}
