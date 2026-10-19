package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Agence;

public interface IAgenceService {
    Agence create(Agence entite);
    Agence findById(Long id);
    List<Agence> findAll();
    Agence update(Long id, Agence entite);
    void deleteById(Long id);
}
