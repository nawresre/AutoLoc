package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Vehicule;
import tn.esprit.springhome.repositories.VehiculeRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {
    private final VehiculeRepo repository;

    @Override
    public Vehicule findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return repository.findAll();
    }

    private void validate(Vehicule e) {
        required(e.getImmatriculation(), "L’immatriculation est obligatoire");
        required(e.getMarque(), "La marque est obligatoire");
        required(e.getModele(), "Le modèle est obligatoire");
        nonNegative(e.getTarifJournalier(), "Le tarif journalier");
    }

    @Override
    public Vehicule create(Vehicule entite) {
        if (entite.getIdVehicule() != null) throw new IllegalArgumentException("Un nouvel objet Vehicule ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Vehicule update(Long id, Vehicule entite) {
        validate(entite);
        Vehicule existant = findById(id);
        existant.setImmatriculation(entite.getImmatriculation());
        existant.setMarque(entite.getMarque());
        existant.setModele(entite.getModele());
        existant.setCategorie(entite.getCategorie());
        existant.setTarifJournalier(entite.getTarifJournalier());
        existant.setStatut(entite.getStatut());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Vehicule", id);
        repository.deleteById(id);
    }
}
