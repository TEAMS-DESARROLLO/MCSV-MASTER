package com.bussinesdomain.maestros.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collaborator_practice")
public class CollaboratorPracticeEntity {

    @Id
    @GeneratedValue(generator = "seqCollaboratorPractice", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorPractice", sequenceName = "collaborator_practice_seq", allocationSize = 1)    
    private Long idCollaboratorPractice;
    private Long idCollaborator;
    private Long idPractice;
    private Long idSpecialization;
    private Long idTechnology;
    private String registrationStatus;
    private Long nivel;

    
}
