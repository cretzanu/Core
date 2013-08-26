package com.business.apps.core.data.repo;

import java.util.List;
import java.util.Map;

import com.business.apps.core.data.search.criteria.SearchCriteria;


public interface Repository {
	
	public <T> T create(T entity);
	
	
	
	public <T> T update(T entity);

	public abstract void delete(Object o);
	
	
	public <T> T readObjectById(Class<T> meta, Long id);
	
	public <T> List<T> readObjectsAll(Class<T> meta);
	
	public  List search(SearchCriteria request);
	

	public <T> List<T> readDataByNamedQuery(String namedQueryName,
			Map<String, Object> params, Class<T> expectedResultItemType);
	
	
	
	

}