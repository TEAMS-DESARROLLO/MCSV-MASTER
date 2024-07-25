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
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CollaboratorTechnologyPaginationService implements IPaginationCommons<CollaboratorTechnologyDTO> {

       
    private final  EntityManager entityManager;

    @Override
    public Page<CollaboratorTechnologyDTO> pagination(PaginationModel pagination) {
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
        List<CollaboratorTechnologyDTO> lista = querySelect.getResultList();

        PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

        Page<CollaboratorTechnologyDTO> page = new PageImpl<CollaboratorTechnologyDTO>(lista, pageable, total);

        return page;
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.CollaboratorTechnologyDTO("+
        "a.idCollaboratorTechnology,"+
        "c.idCollaborator as idCollaborator,"+
        "c.names as collaboratorNames"+
        "ct.idRegion as idCatalogTechnology,"+
        "ct.descriptionCatalogTechnology as descriptionCatalogTechnology"+
        "a.collaboratorRank,"+
        "a.evaluatorRank"+
        ") ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM CollaboratorTechnologyEntity a  "+
        " inner join CatalogTechnologyEntity ct on a.catalogTechnology = ct "+
        " inner join CollaboratorEntity c on a.collaborator = c ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idCollaboratorTechnology")){
                sql.append(" AND a.idCollaboratorTechnology = :idCollaboratorTechnology");
            }
            if(filtro.getField().equals("idCollaborator")){
                sql.append(" AND c.idCollaborator = :idCollaborator");
            }
            if(filtro.getField().equals("collaboratorNames")){
                sql.append(" AND upper(c.names) LIKE upper(:collaboratorNames) ");
            }
            if(filtro.getField().equals("idCatalogTechnology")){
                sql.append(" AND ct.idCatalogTechnology = :idCatalogTechnology");
            }
            if(filtro.getField().equals("descriptionCatalogTechnology")){
                sql.append(" AND upper(ct.descriptionCatalogTechnology) LIKE upper(:descriptionCatalogTechnology) ");
            }
            if(filtro.getField().equals("collaboratorRank")){
                sql.append(" AND a.collaboratorRank = :collaboratorRank");
            }
            if(filtro.getField().equals("evaluatorRank")){
                sql.append(" AND a.evaluatorRank = :evaluatorRank");
            }
        }
		sql.append(" AND a.registrationStatus LIKE :registrationStatus ");

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idCollaboratorTechnology")){
                query.setParameter("idCollaboratorTechnology",filtro.getValue() );
            }
            if(filtro.getField().equals("idCollaborator")){
                query.setParameter("idCollaborator",filtro.getValue() );
            }
            if(filtro.getField().equals("collaboratorNames")){
                query.setParameter("collaboratorNames","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idCatalogTechnology")){
                query.setParameter("idCatalogTechnology",filtro.getValue() );
            }
            if(filtro.getField().equals("descriptionCatalogTechnology")){
                query.setParameter("descriptionCatalogTechnology","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("collaboratorRank")){
                query.setParameter("collaboratorRank",filtro.getValue() );
            }
            if(filtro.getField().equals("evaluatorRank")){
                query.setParameter("evaluatorRank",filtro.getValue() );
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
                if(sort.getColName().equals("idCollaboratorTechnology")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.idCollaboratorTechnology " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idCollaborator")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " c.idCollaborator " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("collaboratorNames")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " c.names " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idCatalogTechnology")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " ct.idCatalogTechnology " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("descriptionCatalogTechnology")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " ct.descriptionCatalogTechnology " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("collaboratorRank")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.collaboratorRank " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("evaluatorRank")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.evaluatorRank " + sort.getSort() );
                    flagMore = true;
                }
            }
        }
        return sql;
    }
    
}
