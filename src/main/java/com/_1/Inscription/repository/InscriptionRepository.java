package com._1.Inscription.repository;

import com._1.Inscription.model.Inscription;
import com._1.Inscription.model.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    @Query("SELECT i FROM Inscription i WHERE i.isDeleted = false")
    List<Inscription> findAllNotDeleted();

    @Query("SELECT i FROM Inscription i WHERE i.anneeScolaire = :anneeScolaire")
    List<Inscription> findByAnneeScolaire(AnneeScolaire anneeScolaire);
    
    @Query("SELECT i FROM Inscription i WHERE i.etudiantId = :etudiantId AND i.anneeScolaire = :anneeScolaire AND i.isDeleted = false")
    Optional<Inscription> findByEtudiantIdAndAnneeScolaire(@Param("etudiantId") long etudiantId, @Param("anneeScolaire") AnneeScolaire anneeScolaire);
} 