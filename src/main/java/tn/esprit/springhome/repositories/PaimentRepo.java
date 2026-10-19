package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Paiement;

public interface PaimentRepo extends JpaRepository<Paiement, Long> {
}
