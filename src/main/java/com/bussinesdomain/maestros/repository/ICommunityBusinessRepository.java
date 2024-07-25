package com.bussinesdomain.maestros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bussinesdomain.maestros.models.CommunityEntity;

public interface ICommunityBusinessRepository extends JpaRepository<CommunityEntity,Long>{
    
    @Query("select r from CommunityEntity r where r.region.idRegion = :idRegion  ")
    List<CommunityEntity> underRegion(@Param("idRegion") Long idRegion);
}
