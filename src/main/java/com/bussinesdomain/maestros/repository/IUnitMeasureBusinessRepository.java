package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;

public interface IUnitMeasureBusinessRepository extends JpaRepository<UnitMeasureEntity,Long>{

    @Query("select r from UnitMeasureEntity r where idUnitMeasure = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<UnitMeasureEntity> findActiveById(Long id);

    @Query("select count(*) from UnitMeasureEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from UnitMeasureEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from UnitMeasureEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<UnitMeasureEntity> findAllActive();
}
