package com.bussinesdomain.maestros.services.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.repository.IGenericRepository;


public abstract class CRUDImpl<T,ID> implements IBaseInterfaceService<T,ID>  {

    protected abstract IGenericRepository<T,ID> getRepo();

    @Override
    public Long count() {
        return getRepo().count();
    }

    @Override
    public T create(T entidad) {

        return getRepo().save(entidad);
    }


    @Override
    public List<T> createAll(List<T> entidades) {
        return (List<T>) getRepo().saveAll(entidades);
    }

    @Override
    public void delete(T entidad) {
        getRepo().delete(entidad);
        
    }

    @Override
    public void deleteById(ID id) {
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        try {
            getRepo().deleteById(id) ;
            
        } catch (Exception e) {
            throw new RepositoryException("ERROR WHILE DELETING, CHECK IF THERE ARE FOREIGN KEYS RELATED TO THE ROW");
        }
        
    }

    @Override
    public Boolean exists(ID id) {
        return getRepo().existsById(id) ;
    }

    @Override
    public List<T> getAll() {
        return (List<T>) getRepo().findAll();
    }

    @Override
    public Page<?> pagination(Integer pagenumber, Integer rows, List<SortModel> sortModel, Filter filter) {
        return null;
    }

    @Override
    public Optional<T> readById(ID id) {

        Optional<T> rtn = getRepo().findById(id);

        if (rtn.isEmpty()) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }
        
        return rtn;
    }


    @Override
    public T update(T entidad, ID id) {
        return getRepo().save(entidad);
    }


    
}
