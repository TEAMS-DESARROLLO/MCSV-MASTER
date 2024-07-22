package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "functional_leader")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionalLeaderEntity {

    @Id
    // @GeneratedValue(generator = "seqFunctionalLeader", strategy = GenerationType.IDENTITY)
    // @SequenceGenerator(name = "seqFunctionalLeader", sequenceName = "functional_leader_seq", allocationSize = 1)
    @GeneratedValue(generator = "seqFunctionalLeader", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqFunctionalLeader", sequenceName="functional_leader_seq", allocationSize = 1)
    @Column(name="id_functional_leader")
    private Long idFunctionalLeader;

    @Column(name="names",nullable = false)
    private String names;

}
