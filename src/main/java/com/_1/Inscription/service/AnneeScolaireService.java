package com._1.Inscription.service;

import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.repository.AnneeScolaireRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnneeScolaireService implements IAnneeScolaireService {
    private final AnneeScolaireRepository anneeScolaireRepository;

    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    @Override
    public List<AnneeScolaire> findAll() {
        return anneeScolaireRepository.findAll();
    }

    @Override
    public AnneeScolaire save(AnneeScolaire anneeScolaire) {
        return anneeScolaireRepository.save(anneeScolaire);
    }

    @Override
    public AnneeScolaire findById(long id) {
        return anneeScolaireRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnneeScolaire> findAnneeScolaireEnCours() {
        return anneeScolaireRepository.findAnneeScolaireEnCours();
    }

    @Override
    public List<AnneeScolaire> findAllNotDeleted() {
        return anneeScolaireRepository.findAllNotDeleted();
    }

    @Override
    public void delete(AnneeScolaire anneeScolaire) {
        anneeScolaireRepository.delete(anneeScolaire);
    }

    @Override
    public AnneeScolaire update(long id, AnneeScolaire updatedAnneeScolaire) {
        return anneeScolaireRepository.save(updatedAnneeScolaire);
    }
} 