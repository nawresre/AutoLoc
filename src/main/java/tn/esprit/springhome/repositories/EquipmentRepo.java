package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Equipement;

public interface EquipmentRepo extends JpaRepository<Equipement, Long> {
}
