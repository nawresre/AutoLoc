package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Employe;
import tn.esprit.springhome.repositories.EmployeRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {
    private final EmployeRepo repository;

    @Override
    public Employe findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return repository.findAll();
    }

    private void validate(Employe e) {
        required(e.getNom(), "Le nom de l’employé est obligatoire");
        required(e.getPrenom(), "Le prénom est obligatoire");
        if (e.getRole() == null) throw new IllegalArgumentException("Le rôle est obligatoire");
    }

    @Override
    public Employe create(Employe entite) {
        if (entite.getIdEmploye() != null) throw new IllegalArgumentException("Un nouvel objet Employe ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Employe update(Long id, Employe entite) {
        validate(entite);
        Employe existant = findById(id);
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setRole(entite.getRole());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Employe", id);
        repository.deleteById(id);
    }
}
