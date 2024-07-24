package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.CommunityEntity;

public interface ICommunityBusinessRepository extends JpaRepository<CommunityEntity,Long>{

    @Query("select r from CommunityEntity r where idCommunity = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<CommunityEntity> findActiveById(Long id);

    @Query("select count(*) from CommunityEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from CommunityEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from CommunityEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<CommunityEntity> findAllActive();
}
