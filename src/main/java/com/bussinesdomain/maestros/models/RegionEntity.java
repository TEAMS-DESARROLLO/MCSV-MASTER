package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionEntity {
    @Id
    @GeneratedValue(generator = "seqRegion", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqRegion", sequenceName = "region_seq", allocationSize = 1)
    @Column(name="id_region")
    private Long idRegion;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;
}
