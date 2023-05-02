package edu.fbansept.demo.dao;

import edu.fbansept.demo.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByPrenom(String prenom);

    Optional<Utilisateur> findByLogin(String login);
}
