package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.CommunityEntity;

public interface ICommunityRepository extends IGenericRepository<CommunityEntity,Long> {
    @Query("select r from CommunityEntity r where idCommunity = ?1 and registrationStatus='A' ")
    Optional<CommunityEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from CommunityEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from CommunityEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from CommunityEntity r where registrationStatus='A' ")
    List<CommunityEntity> findAllActive(); 

    @Modifying
    @Query("update CommunityEntity r set registrationStatus='I' where idCommunity = ?1")
    @Override
    void logicalDeleteById(Long id);
}
