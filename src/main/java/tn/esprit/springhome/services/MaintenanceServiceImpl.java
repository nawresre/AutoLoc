package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Maintenance;
import tn.esprit.springhome.repositories.MaintenanceRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {
    private final MaintenanceRepo repository;

    @Override
    public Maintenance findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return repository.findAll();
    }

    private void validate(Maintenance e) {
        if (e.getDateDebut() == null) throw new IllegalArgumentException("La date de début est obligatoire");
        if (e.getDateFin() != null && e.getDateFin().isBefore(e.getDateDebut())) throw new IllegalArgumentException("La fin de maintenance précède le début");
    }

    @Override
    public Maintenance create(Maintenance entite) {
        if (entite.getIdMaintenance() != null) throw new IllegalArgumentException("Un nouvel objet Maintenance ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Maintenance update(Long id, Maintenance entite) {
        validate(entite);
        Maintenance existant = findById(id);
        existant.setDateDebut(entite.getDateDebut());
        existant.setDateFin(entite.getDateFin());
        existant.setDescription(entite.getDescription());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Maintenance", id);
        repository.deleteById(id);
    }
}
