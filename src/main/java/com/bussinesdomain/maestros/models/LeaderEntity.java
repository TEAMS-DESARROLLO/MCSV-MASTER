package com.bussinesdomain.maestros.models;

import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

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
    @JoinColumn(name="id_practice",referencedColumnName="id_practice")
    private PracticeEntity practice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

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
