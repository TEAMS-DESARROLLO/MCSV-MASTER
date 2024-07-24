package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.UnitMeasureEntity;

public interface IUnitMeasureRepository extends IGenericRepository<UnitMeasureEntity,Long> {
    @Override
    @Query("select r from UnitMeasureEntity r where idUnitMeasure = ?1 and registrationStatus='A' ")
    Optional<UnitMeasureEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from UnitMeasureEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from UnitMeasureEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from UnitMeasureEntity r where registrationStatus='A' ")
    List<UnitMeasureEntity> findAllActive();

    @Modifying
    @Query("update UnitMeasureEntity r set registrationStatus='I' where idUnitMeasure = ?1")
    @Override
    void logicalDeleteById(Long id);


}
