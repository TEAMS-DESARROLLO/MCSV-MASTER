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
@Table(name = "sub_practica")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubpracticaEntity {

    @Id
    @GeneratedValue(generator = "seqSubpractica", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqSubpractica", sequenceName = "subpractica_seq", allocationSize = 1)    
    private Long idSubpractica;

    @Column(name = "description_subpractica ")
    private String descriptionSubpractica;

    @ManyToOne
    @JoinColumn(name = "id_community")
    private CommunityEntity comunidadEntity;

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
