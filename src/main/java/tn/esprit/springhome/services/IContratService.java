package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Contrat;

public interface IContratService {
    Contrat create(Contrat entite);
    Contrat findById(Long id);
    List<Contrat> findAll();
    Contrat update(Long id, Contrat entite);
    void deleteById(Long id);
}
