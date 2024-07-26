package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "catalog_technology")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogTechnologyEntity {
    @Id
    @GeneratedValue(generator = "seqCatalogTechnology", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCatalogTechnology", sequenceName = "catalog_technology_seq", allocationSize = 1)
    @Column(name="id_catalog_technology")
    private Long idCatalogTechnology;

    @Column(name="description_catalog_technology",nullable = false)
    private String descriptionCatalogTechnology;

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
