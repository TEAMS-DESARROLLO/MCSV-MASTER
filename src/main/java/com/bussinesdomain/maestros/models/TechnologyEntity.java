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
@Table(name = "technology")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnologyEntity {

    @Id
    @GeneratedValue(generator = "seqTechnology", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqTechnology", sequenceName = "technology_seq", allocationSize = 1)
    @Column(name="id_technology")
    private Long idTechnology;

    @Column(name="name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="id_subpractica")
    private SubpracticaEntity subpracticaEntity;


    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

    @Column(name = "id_user ", nullable = true)
    private long idUser;
    
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
