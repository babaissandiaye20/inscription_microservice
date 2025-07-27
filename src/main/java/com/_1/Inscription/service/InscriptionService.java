package com._1.Inscription.service;

import com._1.Inscription.model.Inscription;
import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.repository.InscriptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService implements IInscriptionService {
    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    @Override
    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Override
    public Inscription findById(long id) {
        return inscriptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inscription> findByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return inscriptionRepository.findByAnneeScolaire(anneeScolaire);
    }

    @Override
    public List<Inscription> findAllNotDeleted() {
        return inscriptionRepository.findAllNotDeleted();
    }

    @Override
    public void delete(Inscription inscription) {
        inscriptionRepository.delete(inscription);
    }

    @Override
    public Inscription update(long id, Inscription updatedInscription) {
        return inscriptionRepository.save(updatedInscription);
    }
    
    @Override
    public Optional<Inscription> findByEtudiantIdAndAnneeScolaire(long etudiantId, AnneeScolaire anneeScolaire) {
        return inscriptionRepository.findByEtudiantIdAndAnneeScolaire(etudiantId, anneeScolaire);
    }
} 