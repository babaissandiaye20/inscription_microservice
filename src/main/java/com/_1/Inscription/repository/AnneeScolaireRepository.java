package com._1.Inscription.repository;

import com._1.Inscription.model.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    @Query("SELECT a FROM AnneeScolaire a WHERE a.encours = true")
    List<AnneeScolaire> findAnneeScolaireEnCours();

    @Query("SELECT a FROM AnneeScolaire a WHERE a.isDeleted = false")
    List<AnneeScolaire> findAllNotDeleted();
} 