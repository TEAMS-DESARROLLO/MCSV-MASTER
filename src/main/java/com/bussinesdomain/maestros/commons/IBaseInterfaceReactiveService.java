package com.bussinesdomain.maestros.commons;

import java.util.List;

import org.springframework.data.domain.Page;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




public interface IBaseInterfaceReactiveService<T, ID> {

	public Page<?> pagination(Integer pagenumber, Integer rows, List<SortModel> sortModel, Filter filter);

	public T create(T entidad);

	public Flux<T> createAll(Flux<T> entidades);

	public Mono<T> readById(ID id);

	public T update(T entidad, ID id);

	public void delete(T entidad);

	public void deleteById(ID id);

	public Boolean exists(ID id);

	public Long count();

	public Flux<T> getAll();

}
