package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Column(name = "id_user ", nullable = true)
    private Integer idUser;
    
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
