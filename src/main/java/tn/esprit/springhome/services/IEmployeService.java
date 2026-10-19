package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Employe;

public interface IEmployeService {
    Employe create(Employe entite);
    Employe findById(Long id);
    List<Employe> findAll();
    Employe update(Long id, Employe entite);
    void deleteById(Long id);
}
