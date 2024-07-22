package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leader")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaderEntity {

    @Id
    @GeneratedValue(generator = "seqLeader", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqLeader", sequenceName = "leader_seq", allocationSize = 1)

    
    @Column(name="id_leader")
    private Long idLeader;

    @Column(name="names",nullable = false)
    private String names;

    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_community",referencedColumnName="id_community")
    private CommunityEntity community;
}
