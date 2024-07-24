package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.TechnologyEntity;

public interface ITechnologyRepository extends IGenericRepository<TechnologyEntity,Long> {
    @Override
    @Query("select r from TechnologyEntity r where idTechnology = ?1 and registrationStatus='A' ")
    Optional<TechnologyEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from TechnologyEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from TechnologyEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from TechnologyEntity r where registrationStatus='A' ")
    List<TechnologyEntity> findAllActive(); 

    @Modifying
    @Query("update TechnologyEntity r set registrationStatus='I' where idTechnology = ?1")
    @Override
    void logicalDeleteById(Long id);
}
