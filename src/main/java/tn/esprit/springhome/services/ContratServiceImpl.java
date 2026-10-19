package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Contrat;
import tn.esprit.springhome.repositories.ContractRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {
    private final ContractRepo repository;

    @Override
    public Contrat findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return repository.findAll();
    }

    private void validate(Contrat e) {
        nonNegative(e.getMontantTotal(), "Le montant total");
    }

    @Override
    public Contrat create(Contrat entite) {
        if (entite.getIdContrat() != null) throw new IllegalArgumentException("Un nouvel objet Contrat ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Contrat update(Long id, Contrat entite) {
        validate(entite);
        Contrat existant = findById(id);
        existant.setDateSignature(entite.getDateSignature());
        existant.setMontantTotal(entite.getMontantTotal());
        existant.setValide(entite.isValide());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Contrat", id);
        repository.deleteById(id);
    }
}
