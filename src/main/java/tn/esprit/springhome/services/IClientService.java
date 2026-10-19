package tn.esprit.springhome.services;

import java.util.List;
import tn.esprit.springhome.entities.Client;

public interface IClientService {
    Client create(Client entite);
    Client findById(Long id);
    List<Client> findAll();
    Client update(Long id, Client entite);
    void deleteById(Long id);
}
