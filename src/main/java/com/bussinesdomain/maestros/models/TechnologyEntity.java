package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
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
}
