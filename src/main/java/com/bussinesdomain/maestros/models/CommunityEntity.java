package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;


@Entity
@Table(name = "community")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "seqCommunity", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCommunity", sequenceName = "community_seq", allocationSize = 1)
    @Column(name = "id_community")
    private Long idCommunity;

    //@Column(name="description",nullable = false)
    private String description;

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
