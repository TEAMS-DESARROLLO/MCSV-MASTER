package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator_technology")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollaboratorTechnologyEntity {

    @Id
    @GeneratedValue(generator = "seqCollaboratorTechnology", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorTechnology", sequenceName = "collaborator_technology_seq", allocationSize = 1)
    @Column(name="id_collaborator_technology")
    private Long idCollaboratorTechnology;

    @ManyToOne
    @JoinColumn(name="id_collaborator")
    private CollaboratorEntity collaborator;

    @ManyToOne
    @JoinColumn(name="id_catalog_technology")
    private CatalogTechnologyEntity catalogTechnology;

    @Column(name="collaborator_rank")
    private Integer collaboratorRank;

    @Column(name="evaluator_rank")
    private Integer evaluatorRank;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

    @PrePersist
    public void prePersisten(){
        this.registrationStatus=RegistrationStatus.ACTIVE;
        this.createdAt=LocalDateTime.now();
    }
    @PreUpdate
    public void preModify(){
        this.updatedAt = LocalDateTime.now();
    }


}
