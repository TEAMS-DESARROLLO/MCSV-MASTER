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
@Table(name = "collaborator_community")
public class CollaboratorCommunityEntity {

    @Id
    @GeneratedValue(generator = "seqCollaboratorCommunity", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorCommunity", sequenceName = "collaborator_community_seq", allocationSize = 1)    
    private Long idCollaboratorCommunity;
    private Long idCollaborator;
    private Long idPractice;
    private Long idSpecialization;
    private Long idTechnology;
    private String registrationStatus;
    private Long nivel;

    
}
