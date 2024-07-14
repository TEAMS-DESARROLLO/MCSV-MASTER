package com.bussinesdomain.maestros.models;

import com.bussinesdomain.maestros.enums.CollaboratorState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollaboratorEntity {

    @Id
    @GeneratedValue(generator = "seqCollaborator", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaborator", sequenceName = "collaborator_seq", allocationSize = 1)
    @Column(name="id_collaborator")
    private Long idCollaborator;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="names",nullable = false)
    private String names;

    @Column(name="email",nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="state",nullable = false)
    private CollaboratorState state;

    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_leader",referencedColumnName="id_leader")
    private LeaderEntity leader;

    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_rol",referencedColumnName="id_rol")
    private RolEntity rol;

    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_region",referencedColumnName="id_region")
    private RegionEntity region;

    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_functional_leader",referencedColumnName="id_functional_leader")
    private FunctionalLeaderEntity functionalLeader;
}
