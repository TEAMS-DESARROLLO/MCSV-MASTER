package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bussinesdomain.maestros.constants.RegistrationStatus;


@Entity
@Table(name = "practice")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PracticeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPractice", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqPractice", sequenceName = "practice_seq", allocationSize = 1)
    @Column(name = "id_practice")
    private Long idPractice;

    //@Column(name="description",nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name="registration_status ")
    private String registrationStatus;
    
    @ManyToOne(optional = true,fetch= FetchType.EAGER)
    @JoinColumn(name="id_region",referencedColumnName="id_region")
    private RegionEntity region;

    @Column(name="id_user ")    
    private Long idUser;

    public PracticeEntity(Long idPractice, String description){
        this.idPractice=idPractice;
        this.description=description;
    }

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
