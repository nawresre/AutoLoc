package tn.esprit.springhome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springhome.entities.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
