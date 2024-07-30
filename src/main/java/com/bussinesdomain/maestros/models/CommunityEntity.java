package com.bussinesdomain.maestros.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    
    @ManyToOne(optional = true,fetch= FetchType.EAGER)
    @JoinColumn(name="id_region",referencedColumnName="id_region")
    private RegionEntity region;

    @Column(name = "id_user ", nullable = true)
    private Long idUser;
    
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
