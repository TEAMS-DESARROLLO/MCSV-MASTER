package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.LeaderEntity;

public interface ILeaderBusinessRepository extends JpaRepository<LeaderEntity,Long>{

    @Query("select r from LeaderEntity r where idLeader = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<LeaderEntity> findActiveById(Long id);

    @Query("select count(*) from LeaderEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from LeaderEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from LeaderEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<LeaderEntity> findAllActive();
}