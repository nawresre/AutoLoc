package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Contrat;

public interface ContractRepo extends JpaRepository<Contrat, Long> {
}
