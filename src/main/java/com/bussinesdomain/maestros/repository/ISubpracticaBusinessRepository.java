package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.SubpracticaEntity;

public interface ISubpracticaBusinessRepository  extends JpaRepository<SubpracticaEntity,Long>{

    @Query("select r from SubpracticaEntity r where idSubpractica = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<SubpracticaEntity> findActiveById(Long id);

    @Query("select count(*) from SubpracticaEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from SubpracticaEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from SubpracticaEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<SubpracticaEntity> findAllActive();
    
    @Query("select case when count(r) > 0 then true else false end from SubpracticaEntity r where r.comunidadEntity.idCommunity = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underCommunity(Long idCommunity);
}