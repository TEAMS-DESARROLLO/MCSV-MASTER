package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolEntity {

    @Id
    @GeneratedValue(generator = "seqRol", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqRol", sequenceName = "rol_seq", allocationSize = 1)
    @Column(name="id_rol")
    private Long idRol;

    @Column(name="codigo_rol",nullable = false,unique = true)
    private String codigoRol;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;
}
