package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Equipement;
import tn.esprit.springhome.repositories.EquipmentRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {
    private final EquipmentRepo repository;

    @Override
    public Equipement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {
        return repository.findAll();
    }

    private void validate(Equipement e) {
        required(e.getLibelle(), "Le libellé est obligatoire");
    }

    @Override
    public Equipement create(Equipement entite) {
        if (entite.getIdEquipement() != null) throw new IllegalArgumentException("Un nouvel objet Equipement ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Equipement update(Long id, Equipement entite) {
        validate(entite);
        Equipement existant = findById(id);
        existant.setLibelle(entite.getLibelle());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Equipement", id);
        repository.deleteById(id);
    }
}
