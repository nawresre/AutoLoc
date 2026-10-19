package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Agence;

public interface AgenceRepo extends JpaRepository<Agence, Long> {
}
