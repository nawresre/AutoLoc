package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
