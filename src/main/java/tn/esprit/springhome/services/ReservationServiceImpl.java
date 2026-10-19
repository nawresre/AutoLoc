package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Reservation;
import tn.esprit.springhome.repositories.ReservationRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    private final ReservationRepo repository;

    @Override
    public Reservation findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    private void validate(Reservation e) {
        if (e.getDateDebut() == null || e.getDateFin() == null || e.getDateFin().isBefore(e.getDateDebut())) throw new IllegalArgumentException("Dates de réservation invalides");
    }

    @Override
    public Reservation create(Reservation entite) {
        if (entite.getIdReservation() != null) throw new IllegalArgumentException("Un nouvel objet Reservation ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Reservation update(Long id, Reservation entite) {
        validate(entite);
        Reservation existant = findById(id);
        existant.setDateDebut(entite.getDateDebut());
        existant.setDateFin(entite.getDateFin());
        existant.setStatut(entite.getStatut());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Reservation", id);
        repository.deleteById(id);
    }
}
