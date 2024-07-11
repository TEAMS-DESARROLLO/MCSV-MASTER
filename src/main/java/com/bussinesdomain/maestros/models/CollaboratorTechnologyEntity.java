package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator_technology")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollaboratorTechnologyEntity {

    @Id
    @GeneratedValue(generator = "seqCollaboratorTechnology", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorTechnology", sequenceName = "collaborator_technology_seq", allocationSize = 1)
    @Column(name="id_collaborator_technology")
    private Long idCollaboratorTechnology;

    @ManyToOne(optional = false,fetch= FetchType.LAZY)
    @JoinColumn(name="id_collaborator",referencedColumnName="id_collaborator")
    private CollaboratorEntity collaborator;

    @ManyToOne(optional = false,fetch= FetchType.LAZY)
    @JoinColumn(name="id_technology",referencedColumnName="id_technology")
    private TechnologyEntity technology;
}
