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
import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.dto.CommunityDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CommunityPaginationService implements IPaginationCommons<CommunityDTO> {

       
    private final  EntityManager entityManager;

    @Override
    public Page<CommunityDTO> pagination(PaginationModel pagination) {
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
        List<CommunityDTO> lista = querySelect.getResultList();

        PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

        Page<CommunityDTO> page = new PageImpl<CommunityDTO>(lista, pageable, total);

        return page;
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.CommunityDTO("+
        "a.idCommunity,"+
        "a.description,"+
        "r.idRegion as idRegion,"+
        "r.description as regionDescription"+
        ") ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM CommunityEntity a  "+
        " inner join RegionEntity r on a.region = r ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idCommunity")){
                sql.append(" AND a.idCommunity = :idCommunity");
            }
            if(filtro.getField().equals("description")){
                sql.append(" AND upper(a.description) LIKE upper(:description) ");
            }
            if(filtro.getField().equals("idRegion")){
                sql.append(" AND r.idRegion = :idRegion");
            }
            if(filtro.getField().equals("regionDescription")){
                sql.append(" AND upper(r.description) LIKE upper(:regionDescription) ");
            }
        }
		sql.append(" AND a.registrationStatus LIKE :registrationStatus ");

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idCommunity")){
                query.setParameter("idCommunity",filtro.getValue() );
            }
            if(filtro.getField().equals("description")){
                query.setParameter("description","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idRegion")){
                query.setParameter("idRegion",filtro.getValue() );
            }
            if(filtro.getField().equals("regionDescription")){
                query.setParameter("regionDescription","%"+filtro.getValue()+"%");
            }
        }
		query.setParameter("registrationStatus",RegistrationStatus.ACTIVE );
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel>  sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(sorts.size() > 0){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idCommunity")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.idCommunity " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("description")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.description " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idRegion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idRegion " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("regionDescription")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.description " + sort.getSort() );
                    flagMore = true;
                }
            }
        }
        return sql;
    }
    
}
