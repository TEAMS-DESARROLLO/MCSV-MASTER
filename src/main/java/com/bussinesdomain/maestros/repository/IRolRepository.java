package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.RolEntity;

public interface IRolRepository extends IGenericRepository<RolEntity,Long> {

    @Override
    @Query("select r from RolEntity r where idRol = ?1 and registrationStatus='A' ")
    Optional<RolEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from RolEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from RolEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from RolEntity r where registrationStatus='A' ")
    List<RolEntity> findAllActive(); 

    @Modifying
    @Query("update RolEntity r set registrationStatus='I' where idRol = ?1")
    @Override
    void logicalDeleteById(Long id);
}


    
    
}
