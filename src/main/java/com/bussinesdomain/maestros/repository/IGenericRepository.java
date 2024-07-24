package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IGenericRepository<T,ID> extends JpaRepository<T,ID> {
    Optional<T> findActiveById(ID id);

    Long countActive();
    Boolean existsActiveById(ID id);
    List<T> findAllActive(); 
    void logicalDeleteById(ID id);
}
