package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.RegionEntity;

public interface IRegionRepository extends IGenericRepository<RegionEntity,Long> {@Override
    @Query("select r from RegionEntity r where idRegion = ?1 and registrationStatus='A' ")
    Optional<RegionEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from RegionEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from RegionEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from RegionEntity r where registrationStatus='A' ")
    List<RegionEntity> findAllActive(); 

    @Modifying
    @Query("update RegionEntity r set registrationStatus='I' where idRegion = ?1")
    @Override
    void logicalDeleteById(Long id);
}
