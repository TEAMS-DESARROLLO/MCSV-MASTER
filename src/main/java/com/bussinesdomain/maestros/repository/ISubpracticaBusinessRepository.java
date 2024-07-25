package com.bussinesdomain.maestros.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bussinesdomain.maestros.models.SubpracticaEntity;

public interface ISubpracticaBusinessRepository  extends JpaRepository<SubpracticaEntity,Long>{
    
    @Query("select r from SubpracticaEntity r where r.comunidadEntity.idCommunity = :idCommunity  ")
    List<SubpracticaEntity> underCommunity(@Param("idCommunity")Long idCommunity);
}