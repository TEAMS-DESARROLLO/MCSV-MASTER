package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "status_collaborator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusCollaboratorEntity {
    @Id    @GeneratedValue(generator = "seqStatusCollaborator", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqStatusCollaborator", sequenceName = "status_collaborator_seq", allocationSize = 1)
    @Column(name="id_status_collaborator")
    private Long idStatusCollaborator;

    @Column(name="description_status_collaborator",nullable = false)
    private String descriptionStatusCollaborator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

    @Column(name = "id_user ", nullable = true)
    private Long idUser;
    
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
