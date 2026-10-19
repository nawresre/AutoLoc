package tn.esprit.springhome.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springhome.entities.Paiement;
import tn.esprit.springhome.repositories.PaimentRepo;
import tn.esprit.springhome.exception.ResourceNotFoundException;
import static tn.esprit.springhome.services.ServiceValidation.*;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private final PaimentRepo repository;

    @Override
    public Paiement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }

    @Override
    public List<Paiement> findAll() {
        return repository.findAll();
    }
}
