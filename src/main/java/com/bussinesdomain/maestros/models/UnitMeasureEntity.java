package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unit_measure")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitMeasureEntity {
    @Id
    @GeneratedValue(generator = "seqUnitMeasure", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqUnitMeasure", sequenceName = "unit_measure_seq", allocationSize = 1)
    @Column(name="id_unit_measure")
    private Long idUnitMeasure;

    @Column(name="name",nullable = false)
    private String name;

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
