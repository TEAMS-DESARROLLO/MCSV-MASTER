package com.bussinesdomain.maestros.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IBaseInterfaceReactiveService;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.models.CollaboratorEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ICollaboratorReactivoService implements IBaseInterfaceReactiveService<CollaboratorEntity, Long> {

    @Override
    public Page<?> pagination(Integer pagenumber, Integer rows, List<SortModel> sortModel, Filter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagination'");
    }

    @Override
    public CollaboratorEntity create(CollaboratorEntity entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Flux<CollaboratorEntity> createAll(Flux<CollaboratorEntity> entidades) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAll'");
    }

    @Override
    public Mono<CollaboratorEntity> readById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readById'");
    }

    @Override
    public CollaboratorEntity update(CollaboratorEntity entidad, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(CollaboratorEntity entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Boolean exists(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public Long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public Flux<CollaboratorEntity> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }



}
