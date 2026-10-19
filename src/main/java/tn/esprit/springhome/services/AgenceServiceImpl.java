package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Agence;
import tn.esprit.springhome.repositories.AgenceRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepo repository;

    @Override
    public Agence findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return repository.findAll();
    }

    private void validate(Agence e) {
        required(e.getNom(), "Le nom de l’agence est obligatoire");
        required(e.getVille(), "La ville est obligatoire");
        required(e.getAdresse(), "L’adresse est obligatoire");
        required(e.getTelephone(), "Le téléphone est obligatoire");
    }

    @Override
    public Agence create(Agence entite) {
        if (entite.getIdAgence() != null) throw new IllegalArgumentException("Un nouvel objet Agence ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Agence update(Long id, Agence entite) {
        validate(entite);
        Agence existant = findById(id);
        existant.setNom(entite.getNom());
        existant.setVille(entite.getVille());
        existant.setAdresse(entite.getAdresse());
        existant.setTelephone(entite.getTelephone());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Agence", id);
        repository.deleteById(id);
    }
}
