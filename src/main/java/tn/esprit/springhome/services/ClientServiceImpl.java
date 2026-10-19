package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Client;
import tn.esprit.springhome.repositories.ClientRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepo repository;

    @Override
    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    private void validate(Client e) {
        required(e.getNom(), "Le nom du client est obligatoire");
        required(e.getPrenom(), "Le prénom est obligatoire");
        required(e.getEmail(), "L’e-mail est obligatoire");
        required(e.getNumPermis(), "Le numéro de permis est obligatoire");
    }

    @Override
    public Client create(Client entite) {
        if (entite.getIdClient() != null) throw new IllegalArgumentException("Un nouvel objet Client ne doit pas avoir d’identifiant");
        validate(entite);
        return repository.save(entite);
    }

    @Override
    public Client update(Long id, Client entite) {
        validate(entite);
        Client existant = findById(id);
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setEmail(entite.getEmail());
        existant.setTelephone(entite.getTelephone());
        existant.setNumPermis(entite.getNumPermis());
        existant.setDateInscription(entite.getDateInscription());
        // Ne pas remplacer les collections JPA ni l'identifiant.
        return repository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Client", id);
        repository.deleteById(id);
    }
}
