package com.bussinesdomain.maestros.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bussinesdomain.maestros.models.LeaderEntity;

public interface ILeaderRepository extends IGenericRepository<LeaderEntity,Long> {


    @Query("SELECT l FROM LeaderEntity l WHERE l.practice.idPractice = :idPractice")
    LeaderEntity getLeaderEntityByIdPractice(@Param("idPractice") Long idPractice);
}
