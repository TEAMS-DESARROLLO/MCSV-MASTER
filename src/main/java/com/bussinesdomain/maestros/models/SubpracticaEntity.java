package com.bussinesdomain.maestros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

}
