package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.dto.LeaderDTO;
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
public class LeaderPaginationService implements IPaginationCommons<LeaderDTO> {

    private final EntityManager entityManager;
    @Override
    public Page<LeaderDTO> pagination(PaginationModel pagination) {
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
            List<LeaderDTO> lista = querySelect.getResultList();

            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

            Page<LeaderDTO> page = new PageImpl<LeaderDTO>(lista, pageable, total);

            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error al momento de generar la paginacion " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.LeaderDTO(a.idLeader,a.names,cm.idCommunity,cm.description as communityDescription) ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM LeaderEntity a  join CommunityEntity cm on a.community.idCommunity = cm.idCommunity ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idLeader")){
                sql.append(" AND a.idLeader = :idLeader");
            }
            if(filtro.getField().equals("names")){
                sql.append(" AND a.names LIKE :names ");
            }
            if(filtro.getField().equals("idCommunity")){
                sql.append(" AND cm.idCommunity = :idCommunity ");
            }
            if(filtro.getField().equals("communityDescription")){
                sql.append(" AND cm.description LIKE :communityDescription ");
            }
        }

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idLeader")){
                query.setParameter("idLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("names")){
                query.setParameter("names","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idCommunity")){
                query.setParameter("idCommunity",filtro.getValue() );
            }
            if(filtro.getField().equals("communityDescription")){
                query.setParameter("communityDescription","%"+filtro.getValue()+"%");
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
                if(sort.getColName().equals("idLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.idLeader " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("names")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.names " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idCommunity")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " cm.idCommunity " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("communityDescription")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " cm.description " + sort.getSort() );
                    flagMore = true;
                }
            }
        }
        return sql;
    }
}
