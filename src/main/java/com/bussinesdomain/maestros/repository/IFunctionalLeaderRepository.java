package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;

public interface IFunctionalLeaderRepository extends IGenericRepository<FunctionalLeaderEntity,Long> {
    @Query("select r from FunctionalLeaderEntity r where idFunctionalLeader = ?1 and registrationStatus='A' ")
    Optional<FunctionalLeaderEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from FunctionalLeaderEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from FunctionalLeaderEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from FunctionalLeaderEntity r where registrationStatus='A' ")
    List<FunctionalLeaderEntity> findAllActive(); 

    @Modifying
    @Query("update FunctionalLeaderEntity r set registrationStatus='I' where idFunctionalLeader = ?1")
    @Override
    void logicalDeleteById(Long id);
}
