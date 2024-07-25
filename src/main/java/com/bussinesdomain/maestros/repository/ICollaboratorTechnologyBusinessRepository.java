package com.bussinesdomain.maestros.repository;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICollaboratorTechnologyBusinessRepository extends JpaRepository<CollaboratorTechnologyEntity,Long>{

    @Query("select r from CollaboratorTechnologyEntity r where idCollaboratorTechnology = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<CollaboratorTechnologyEntity> findActiveById(Long id);

    @Query("select count(*) from CollaboratorTechnologyEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from CollaboratorTechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from CollaboratorTechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<CollaboratorTechnologyEntity> findAllActive();
}
