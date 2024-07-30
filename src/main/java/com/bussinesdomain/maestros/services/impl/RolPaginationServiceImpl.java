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
import com.bussinesdomain.maestros.dto.RolResponseDTO;
import com.bussinesdomain.maestros.exception.ServiceException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolPaginationServiceImpl implements IPaginationCommons<RolResponseDTO> {
	
	private final EntityManager entityManager;
	
	@Override
	public Page<RolResponseDTO> pagination(PaginationModel pagination) {
		try {

			String sqlCount = "SELECT count(r) " + getFrom().toString() + getFilters(pagination.getFilters()).toString();
			String sqlSelect = getSelect().toString() + getFrom().toString() + getFilters(pagination.getFilters()).toString() + getOrder(pagination.getSorts());

			Query queryCount = entityManager.createQuery(sqlCount);
			Query querySelect = entityManager.createQuery(sqlSelect);

			this.setParams(pagination.getFilters(), queryCount);
			this.setParams(pagination.getFilters(), querySelect);

			Long total = (long) queryCount.getSingleResult();

			querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
			querySelect.setMaxResults(pagination.getRowsPerPage());

			@SuppressWarnings("unchecked")
			List<RolResponseDTO> lista = querySelect.getResultList();

			PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

			Page<RolResponseDTO> page = new PageImpl<RolResponseDTO>(lista, pageable, total);

			return page;
		} catch (RuntimeException e) {
			throw new ServiceException("error when generating the pagination " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public StringBuilder getSelect() {
		StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.RolResponseDTO(r.idRol,r.description,r.codigoRol) ");
        return sql;
	}

	@Override
	public StringBuilder getFrom() {
		StringBuilder sql = new StringBuilder(" FROM RolEntity r  ");
        return sql;
	}

	@Override
	public StringBuilder getFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idRol")){
                sql.append(" AND r.idRol = :idRol");
            }
            if(filtro.getField().equals("description")){
                sql.append(" AND r.description LIKE :description ");
            }
        }

        return sql;
	}

	@Override
	public Query setParams(List<Filter> filters, Query query) {
		for(Filter filtro:filters){
            if(filtro.getField().equals("idRol")){
                query.setParameter("idRol",filtro.getValue() );
            }
            if(filtro.getField().equals("description")){
                query.setParameter("description","%"+filtro.getValue()+"%");
            }
        }
        return query;
	}

	@Override
	public StringBuilder getOrder(List<SortModel> sorts) {
		boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(!sorts.isEmpty()){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idRol")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " idRol " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("description")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " description " + sort.getSort() );
                    flagMore = true;
                }
           }
        }
         return sql;
	}
	
	

}
