package com.bussinesdomain.maestros.models;

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

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;
}
