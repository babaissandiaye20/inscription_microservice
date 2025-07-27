# Opened Files
## File Name
src/main/java/com/_1/Inscription/service/InscriptionService.java
## File Content
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
# Opened Files
## File Name
src/main/java/com/_1/Inscription/service/IInscriptionService.java
## File Content
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
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/CreateAnneeScolaireResponse.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAnneeScolaireResponse {
    private long id;
    private String libelle;
    private boolean encours;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/CreateEtudiantRequest.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEtudiantRequest {
    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;
    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;
    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
    @NotNull(message = "La date de naissance est obligatoire")
    private java.time.LocalDate dateNaissance;
    @NotBlank(message = "Le lieu de naissance est obligatoire")
    private String lieuNaissance;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/CreateEtudiantResponse.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEtudiantResponse {
    private long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private LocalDate dateNaissance;
    private String lieuNaissance;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/ErrorDto.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private String code;
    private String message;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/CreateInscriptionResponse.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInscriptionResponse {
    private long id;
    private LocalDate date;
    private Long classeId;
    private Long etudiantId;
    private Long anneeScolaireId;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/dto/CreateInscriptionRequest.java
## File Content
package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInscriptionRequest {
    @NotNull(message = "La date d'inscription est obligatoire")
    private LocalDate date;
    @NotNull(message = "L'identifiant de la classe est obligatoire")
    private Long classeId;
    @NotNull(message = "L'identifiant de l'étudiant est obligatoire")
    private Long etudiantId;
    @NotNull(message = "L'année scolaire est obligatoire")
    private Long anneeScolaireId;
} 
# Opened Files
## File Name
src/main/java/com/_1/Inscription/repository/InscriptionRepository.java
## File Content
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
# Opened Files
## File Name
src/main/java/com/_1/Inscription/model/AnneeScolaire.java
## File Content
package com._1.Inscription.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnneeScolaire extends BaseModels {
    @Column(length = 15)
    private String libelle;
    private boolean encours;
} 
