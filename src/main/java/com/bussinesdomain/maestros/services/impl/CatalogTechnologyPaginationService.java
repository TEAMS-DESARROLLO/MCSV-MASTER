package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.dto.CatalogTechnologyResponseDTO;
import com.bussinesdomain.maestros.exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogTechnologyPaginationService implements IPaginationCommons<CatalogTechnologyResponseDTO> {
		
	private final EntityManager entityManager;
	
	@Override
	public Page<CatalogTechnologyResponseDTO> pagination(PaginationModel pagination) {
		try {

			String sqlCount = "SELECT count(u) " + getFrom().toString() + getFilters(pagination.getFilters()).toString();
			String sqlSelect = getSelect().toString() + getFrom().toString() + getFilters(pagination.getFilters()).toString() + getOrder(pagination.getSorts());

			Query queryCount = entityManager.createQuery(sqlCount);
			Query querySelect = entityManager.createQuery(sqlSelect);

			this.setParams(pagination.getFilters(), queryCount);
			this.setParams(pagination.getFilters(), querySelect);

			Long total = (long) queryCount.getSingleResult();

			querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
			querySelect.setMaxResults(pagination.getRowsPerPage());

			@SuppressWarnings("unchecked")
			List<CatalogTechnologyResponseDTO> lista = querySelect.getResultList();

			PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

			Page<CatalogTechnologyResponseDTO> page = new PageImpl<CatalogTechnologyResponseDTO>(lista, pageable, total);

			return page;
		} catch (RuntimeException e) {
			throw new ServiceException("error when generating the pagination " + e.getMessage());
		}
	}

	@Override
	public StringBuilder getSelect() {
		StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.CatalogTechnologyResponseDTO(u.idCatalogTechnology,u.descriptionCatalogTechnology) ");
        return sql;
	}

	@Override
	public StringBuilder getFrom() {
		StringBuilder sql = new StringBuilder(" FROM CatalogTechnologyEntity u  ");
        return sql;
	}

	@Override
	public StringBuilder getFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idCatalogTechnology")){
                sql.append(" AND u.idCatalogTechnology = :idCatalogTechnology");
            }
            if(filtro.getField().equals("descriptionCatalogTechnology")){
                sql.append(" AND upper(u.descriptionCatalogTechnology) LIKE upper(:descriptionCatalogTechnology) ");
            }
        }
		
		sql.append(" AND u.registrationStatus LIKE :registrationStatus ");

        return sql;
	}

	@Override
	public Query setParams(List<Filter> filters, Query query) {
		for(Filter filtro:filters){
            if(filtro.getField().equals("idCatalogTechnology")){
                query.setParameter("idCatalogTechnology",filtro.getValue() );
            }
            if(filtro.getField().equals("descriptionCatalogTechnology")){
                query.setParameter("descriptionCatalogTechnology","%"+filtro.getValue()+"%");
            }
        }
		
		query.setParameter("registrationStatus",RegistrationStatus.ACTIVE );

        return query;
	}

	@Override
	public StringBuilder getOrder(List<SortModel> sorts) {
		 boolean flagMore = false;
	        StringBuilder sql = new StringBuilder("");
	        if(!sorts.isEmpty()){
	            sql.append(" ORDER BY ");

	            for(SortModel sort:sorts){
	                if(sort.getColName().equals("idCatalogTechnology")){
	                    if(flagMore)
	                        sql.append(", ");

	                    sql.append( " idCatalogTechnology " + sort.getSort() );
	                    flagMore = true;
	                }

	                if(sort.getColName().equals("descriptionCatalogTechnology")){
	                    if(flagMore)
	                        sql.append(", ");
	                    sql.append( " descriptionCatalogTechnology " + sort.getSort() );
	                    flagMore = true;
	                }
	           }
	        }
	         return sql;
	}
	

	
	

}
