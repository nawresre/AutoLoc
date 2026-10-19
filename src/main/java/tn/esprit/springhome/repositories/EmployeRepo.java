package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Employe;

public interface EmployeRepo extends JpaRepository<Employe, Long> {
}
