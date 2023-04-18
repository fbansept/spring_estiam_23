package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateur() {

        List<Utilisateur> listeUtilisateur = utilisateurDao.findAll();
        return listeUtilisateur;
    }

    @PostMapping("/utilisateur")
    public boolean ajoutUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurDao.save(utilisateur);
        return true;
    }

}
