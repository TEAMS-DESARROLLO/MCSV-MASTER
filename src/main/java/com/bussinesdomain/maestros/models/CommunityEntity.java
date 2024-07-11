package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "community")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityEntity {

    @Id
    @GeneratedValue(generator = "seqCommunity", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCommunity", sequenceName = "community_seq", allocationSize = 1)
    @Column(name = "id_community")
    private Long idCommunity;

    @Column(name="description",nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }
    @PreUpdate
    public void preModify(){
        this.updatedAt = LocalDateTime.now();
    }
}
