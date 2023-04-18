package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateurs() {

        List<Utilisateur> listeUtilisateur = utilisateurDao.findAll();
        return listeUtilisateur;
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int id) {
        Optional<Utilisateur> optional = utilisateurDao.findById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> editUtilisateur(@RequestBody Utilisateur utilisateur) {

        if(utilisateur.getId() != null) {
            Optional<Utilisateur> utilisateurBdd = utilisateurDao.findById(utilisateur.getId());

            if(utilisateurBdd.isPresent()) {
                utilisateurDao.save(utilisateur);
                return new ResponseEntity<>(utilisateur,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur,HttpStatus.CREATED);
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Integer> supprimeUtilisateur (@PathVariable int id) {

        Optional<Utilisateur> utilisateurBdd = utilisateurDao.findById(id);

        if(utilisateurBdd.isPresent()) {
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
