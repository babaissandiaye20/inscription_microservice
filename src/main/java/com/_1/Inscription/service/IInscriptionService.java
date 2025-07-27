package com._1.Inscription.service;

import com._1.Inscription.model.Inscription;
import com._1.Inscription.model.AnneeScolaire;
import java.util.List;
import java.util.Optional;

public interface IInscriptionService {
    List<Inscription> findAll();
    Inscription save(Inscription inscription);
    Inscription findById(long id);
    List<Inscription> findByAnneeScolaire(AnneeScolaire anneeScolaire);
    List<Inscription> findAllNotDeleted();
    void delete(Inscription inscription);
    Inscription update(long id, Inscription inscription);
    Optional<Inscription> findByEtudiantIdAndAnneeScolaire(long etudiantId, AnneeScolaire anneeScolaire);
} 