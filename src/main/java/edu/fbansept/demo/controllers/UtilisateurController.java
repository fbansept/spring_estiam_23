package edu.fbansept.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.models.Utilisateur;
import edu.fbansept.demo.security.AppUserDetails;
import edu.fbansept.demo.security.JwtUtils;
import edu.fbansept.demo.views.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            UserDetails userDetails = (UserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getLogin(),
                            utilisateur.getPassword()))
                    .getPrincipal();

            return new ResponseEntity<>(jwtUtils.generateJwt(userDetails), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/admin/utilisateurs")
    @JsonView(VueUtilisateur.class)
    public List<Utilisateur> getUtilisateurs() {

        List<Utilisateur> listeUtilisateur = utilisateurDao.findAll();
        return listeUtilisateur;
    }

    @GetMapping("/utilisateur-par-prenom/{prenom}")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable String prenom) {
        Optional<Utilisateur> optional = utilisateurDao.findByPrenom(prenom);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/utilisateur/{id}")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int id) {
        Optional<Utilisateur> optional = utilisateurDao.findById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/utilisateur")
    @JsonView(VueUtilisateur.class)
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
