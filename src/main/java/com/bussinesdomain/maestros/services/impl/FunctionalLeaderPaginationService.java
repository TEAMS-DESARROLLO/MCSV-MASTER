package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;

import com.bussinesdomain.maestros.dto.FunctionalLeaderDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FunctionalLeaderPaginationService implements IPaginationCommons<FunctionalLeaderDTO> {

       
    private final  EntityManager entityManager;

    @Override
    public Page<FunctionalLeaderDTO> pagination(PaginationModel pagination) {
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
        List<FunctionalLeaderDTO> lista = querySelect.getResultList();

        PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

        Page<FunctionalLeaderDTO> page = new PageImpl<FunctionalLeaderDTO>(lista, pageable, total);

        return page;
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.FunctionalLeaderDTO(a.idFunctionalLeader,a.names) ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM FunctionalLeaderEntity a  ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idFunctionalLeader")){
                sql.append(" AND a.idFunctionalLeader = :idFunctionalLeader");
            }
            if(filtro.getField().equals("names")){
                sql.append(" AND upper(a.names) LIKE upper(:names) ");
            }
        }

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idFunctionalLeader")){
                query.setParameter("idFunctionalLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("names")){
                query.setParameter("names","%"+filtro.getValue()+"%");
            }
        }
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel>  sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(sorts.size() > 0){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idFunctionalLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " idFunctionalLeader " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("names")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " names " + sort.getSort() );
                    flagMore = true;
                }
            }
        }
        return sql;
    }
    
}
