package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;

public interface ICatalogTechnologyBusinessRepository extends JpaRepository<CatalogTechnologyEntity,Long>{

    @Query("select r from CatalogTechnologyEntity r where idCatalogTechnology = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<CatalogTechnologyEntity> findActiveById(Long id);

    @Query("select count(*) from CatalogTechnologyEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from CatalogTechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from CatalogTechnologyEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<CatalogTechnologyEntity> findAllActive();
}
