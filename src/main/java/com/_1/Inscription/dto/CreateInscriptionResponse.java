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