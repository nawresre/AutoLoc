package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Maintenance;

public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {
}
