package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

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
    @GeneratedValue(generator = "seqFunctionalLeader", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqFunctionalLeader", sequenceName="functional_leader_seq", allocationSize = 1)
    @Column(name="id_functional_leader")
    private Long idFunctionalLeader;

    @Column(name="names",nullable = false)
    private String names;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

    @Column(name="id_user ", nullable=true)
    private Integer idUser;

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
