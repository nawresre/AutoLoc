package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Vehicule;

public interface VehiculeRepo extends JpaRepository<Vehicule, Long> {
}
