package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.PaysDao;
import edu.fbansept.demo.models.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaysController {

    @Autowired
    private PaysDao paysDao;

    @GetMapping("/liste-pays")
    public List<Pays> getPays() {

        List<Pays> listePays = paysDao.findAll();
        return listePays;
    }

    @GetMapping("/pays/{id}")
    public ResponseEntity<Pays> getPays(@PathVariable int id) {
        Optional<Pays> optional = paysDao.findById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pays")
    public ResponseEntity<Pays> editPays(@RequestBody Pays pays) {

        if(pays.getId() != null) {
            Optional<Pays> paysBdd = paysDao.findById(pays.getId());

            if(paysBdd.isPresent()) {
                paysDao.save(pays);
                return new ResponseEntity<>(pays,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        paysDao.save(pays);
        return new ResponseEntity<>(pays,HttpStatus.CREATED);
    }

    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Integer> supprimePays (@PathVariable int id) {

        Optional<Pays> paysBdd = paysDao.findById(id);

        if(paysBdd.isPresent()) {
            paysDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
