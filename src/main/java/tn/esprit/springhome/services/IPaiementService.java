package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Paiement;

public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
