package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.RegionEntity;

public interface IRegionBusinessRepository extends JpaRepository<RegionEntity,Long>{

    @Query("select r from RegionEntity r where idRegion = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<RegionEntity> findActiveById(Long id);

    @Query("select count(*) from RegionEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from RegionEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from RegionEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<RegionEntity> findAllActive();
}