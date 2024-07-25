package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.TechnologyEntity;

public interface ITechnologyBusinessRepository extends JpaRepository<TechnologyEntity,Long>{

    @Query("select r from TechnologyEntity r where idTechnology = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<TechnologyEntity> findActiveById(Long id);

    @Query("select count(*) from TechnologyEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from TechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from TechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<TechnologyEntity> findAllActive();

    @Query("select case when count(r) > 0 then true else false end from TechnologyEntity r where r.subpracticaEntity.idSubpractica = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underSubpractica(Long idRegion);
}