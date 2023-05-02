package edu.fbansept.demo.security;

import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Utilisateur> optional = utilisateurDao.findByLogin(username);

        if(optional.isPresent()) {
            return new AppUserDetails(optional.get());
        }

        throw new UsernameNotFoundException("Login inconnu");

    }
}
