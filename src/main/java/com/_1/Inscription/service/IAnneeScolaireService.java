package com._1.Inscription.service;

import com._1.Inscription.model.AnneeScolaire;
import java.util.List;

public interface IAnneeScolaireService {
    List<AnneeScolaire> findAll();
    AnneeScolaire save(AnneeScolaire anneeScolaire);
    AnneeScolaire findById(long id);
    List<AnneeScolaire> findAnneeScolaireEnCours();
    List<AnneeScolaire> findAllNotDeleted();
    void delete(AnneeScolaire anneeScolaire);
    AnneeScolaire update(long id, AnneeScolaire anneeScolaire);
} 