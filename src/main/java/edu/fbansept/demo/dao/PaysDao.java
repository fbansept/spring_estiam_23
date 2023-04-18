package edu.fbansept.demo.dao;

import edu.fbansept.demo.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysDao extends JpaRepository<Pays,Integer> {

}
