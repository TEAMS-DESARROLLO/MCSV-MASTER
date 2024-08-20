package com.bussinesdomain.maestros.models;


import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

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
    // @GeneratedValue(generator = "seqCollaborator", strategy = GenerationType.IDENTITY)
    // @SequenceGenerator(name = "seqCollaborator", sequenceName = "collaborator_seq", allocationSize = 1)
    @Column(name="id_collaborator")
    private Long idCollaborator;

    @Column(name="last_name")
    private String lastName;

    @Column(name="names")
    private String names;

    @Column(name="email")
    private String email;

    

    private Integer state;

    @ManyToOne()
    @JoinColumn(name="id_leader")
    private LeaderEntity leader;

    @ManyToOne()
    @JoinColumn(name="id_rol")
    private RolEntity rol;

    @ManyToOne()
    @JoinColumn(name="id_region")
    private RegionEntity region;

    @ManyToOne()
    @JoinColumn(name="id_functional_leader")
    private FunctionalLeaderEntity functionalLeader;

    
    @ManyToOne()
    @JoinColumn(name="id_status_collaborator")
    private StatusCollaboratorEntity statusCollaborator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="registration_status ", nullable=false,length = 1)
    private String registrationStatus;

    @ManyToOne()
    @JoinColumn(name="id_practice")
    private PracticeEntity practice;

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
