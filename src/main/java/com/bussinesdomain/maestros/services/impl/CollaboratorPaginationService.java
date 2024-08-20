package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;

import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
import com.bussinesdomain.maestros.exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CollaboratorPaginationService implements IPaginationCommons<CollaboratorResponseDTO> {

    private final EntityManager entityManager;
    @Override
    public Page<CollaboratorResponseDTO> pagination(PaginationModel pagination) {
        try {

            String sqlCount  = "SELECT count(a) " + getFrom().toString() + getFilters( pagination.getFilters()  ).toString();
            String sqlSelect = getSelect().toString() + getFrom().toString() +getFilters( pagination.getFilters()).toString() + getOrder(pagination.getSorts());

            Query queryCount = entityManager. createQuery(sqlCount);
            Query querySelect = entityManager.createQuery(sqlSelect);

            this.setParams(pagination.getFilters(), queryCount);
            this.setParams(pagination.getFilters(), querySelect);

            Long total = (long) queryCount.getSingleResult();

            querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
            querySelect.setMaxResults(pagination.getRowsPerPage());

            @SuppressWarnings("unchecked")
            List<CollaboratorResponseDTO> lista = querySelect.getResultList();

            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

            Page<CollaboratorResponseDTO> page = new PageImpl<CollaboratorResponseDTO>(lista, pageable, total);

            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error al momento de generar la paginacion " + e.getMessage(), e.getCause() );
        }
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.CollaboratorResponseDTO(a.idCollaborator," +
                "a.lastName," +
                "a.names," +
                "a.email," +
                "a.state, " +
                "l.idLeader," +
                "l.names as leaderNames," +
                "r.idRol," +
                "r.description as rolDescription," +
                "rg.idRegion," +
                "rg.description as regionDescription," +
                "fl.idFunctionalLeader," +
                "fl.names as functionalLeaderNames," +
                "sc.idStatusCollaborator ," +
                "sc.descriptionStatusCollaborator ," +
                "ce.idPractice as idPractice," +
                "ce.description as practiceDescription"  +
                ") ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM CollaboratorEntity a " +
                " left join LeaderEntity l on a.leader=l " +
                " left join RolEntity r on a.rol=r " +
                " left join RegionEntity rg on a.region=rg " +
                " left join FunctionalLeaderEntity fl on a.functionalLeader=fl " +
                " left join StatusCollaboratorEntity sc on a.statusCollaborator = sc " +
                " left join PracticeEntity ce on a.practice = ce " 
                );
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idCollaborator")){
                sql.append(" AND a.idCollaborator = :idCollaborator");
            }
            if(filtro.getField().equals("lastName")){
                sql.append(" AND upper(a.lastName) LIKE upper(:lastName) ");
            }
            if(filtro.getField().equals("names")){
                sql.append(" AND upper(a.names) LIKE upper(:names) ");
            }
            if(filtro.getField().equals("email")){
                sql.append(" AND a.email LIKE :email ");
            }
            if(filtro.getField().equals("state")){
                sql.append(" AND a.state LIKE :state ");
            }

            if(filtro.getField().equals("idLeader")){
                sql.append(" AND l.idLeader = :idLeader ");
            }
            if(filtro.getField().equals("leaderNames")){
                sql.append(" AND upper(l.names) LIKE upper(:leaderNames) ");
            }

            if(filtro.getField().equals("idRol")){
                sql.append(" AND r.idRol = :idRol ");
            }
            if(filtro.getField().equals("rolDescription")){
                sql.append(" AND upper(r.description) LIKE upper(:rolDescription) ");
            }

            if(filtro.getField().equals("idRegion")){
                sql.append(" AND rg.idRegion = :idRegion ");
            }
            if(filtro.getField().equals("regionDescription")){
                sql.append(" AND upper(rg.description) LIKE upper(:regionDescription) ");
            }

            if(filtro.getField().equals("idFunctionalLeader")){
                sql.append(" AND fl.idFunctionalLeader = :idFunctionalLeader ");
            }
            if(filtro.getField().equals("functionalLeaderNames")){
                sql.append(" AND upper(fl.names) LIKE upper(:functionalLeaderNames) ");
            }
        }
        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idCollaborator")){
                query.setParameter("idCollaborator",filtro.getValue() );
            }
            if(filtro.getField().equals("names")){
                query.setParameter("names","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("lastName")){
                query.setParameter("lastName","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("email")){
                query.setParameter("email","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("state")){
                query.setParameter("state",filtro.getValue() );
            }

            if(filtro.getField().equals("idLeader")){
                query.setParameter("idLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("leaderNames")){
                query.setParameter("leaderNames","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("idRol")){
                query.setParameter("idRol",filtro.getValue() );
            }
            if(filtro.getField().equals("rolDescription")){
                query.setParameter("rolDescription","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("idRegion")){
                query.setParameter("idRegion",filtro.getValue() );
            }
            if(filtro.getField().equals("regionDescription")){
                query.setParameter("regionDescription","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("idFunctionalLeader")){
                query.setParameter("idFunctionalLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("functionalLeaderNames")){
                query.setParameter("functionalLeaderNames","%"+filtro.getValue()+"%");
            }
        }
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel> sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(sorts.size() > 0){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idCollaborator")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.idCollaborator " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("lastName")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.lastName " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("names")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.names " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("email")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.email " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("state")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.state " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " l.idLeader " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("leaderNames")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " l.names " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idRol")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idRol " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("rolDescription")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.description " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idRegion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " rg.idRegion " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("regionDescription")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " rg.description " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idFunctionalLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " fl.idFunctionalLeader " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("functionalLeaderNames")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " fl.names " + sort.getSort() );
                    flagMore = true;
                }
            }
        }
        return sql;
    }
}
