package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Vehicule;

public interface IVehiculeService {
    Vehicule create(Vehicule entite);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    Vehicule update(Long id, Vehicule entite);
    void deleteById(Long id);
}
