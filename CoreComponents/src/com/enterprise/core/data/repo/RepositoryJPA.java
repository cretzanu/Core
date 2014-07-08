package com.enterprise.core.data.repo;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.enterprise.core.data.PersistentEntity;
import com.enterprise.core.data.search.criteria.SearchCriteria;
import com.enterprise.core.data.search.engine.SearchEngineFactory;


/**
 * 
 * @author cretuli
 * 
 */
public abstract class RepositoryJPA implements  Repository {
	
	EntityManager em;
	
	public void beginTransaction() {
		this.getEm().getTransaction().begin();
	}

	public void commitTransaction() {
		this.getEm().getTransaction().commit();
	}

	public void rollbackTransaction() {
		if (this.getEm().getTransaction().isActive())
			this.getEm().getTransaction().rollback();

	}

	@Override
	public <T> T create(T entity) {
		this.getEm().persist(entity);
		return entity;
	}
	

	@Override
	public <T> T update(T entity) {
		T managedEntity = this.getEm().merge(entity);
		return managedEntity;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.repo.Repository#delete(java.lang.Object)
	 */
	public void delete(Object o) {
		if (o instanceof PersistentEntity)
			 o = getEm().find(o.getClass(), ((PersistentEntity)o).getId());//mandatory merge detached objects first
		
		this.getEm().remove(o);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T readObjectById(Class<T> meta, Long id) {
		
		String query= new StringBuilder()
						.append(" Select o from ")
						.append(meta.getSimpleName())
						.append(" o where id=:id").toString();
		return (T)this.getEm().createQuery(query).setParameter("id", id).getSingleResult();
		
	}
	@Override
	public <T> List<T> readObjectsAll(Class<T> meta) {
		String query= new StringBuilder()
		.append(" Select o from ")
		.append(meta.getSimpleName())
		.append(" o ")
		.toString();
		return this.getEm().createQuery(query).getResultList();
	}
	@Override
	public <T> List<T> readDataByNamedQuery(String namedQueryName, Map<String,Object> params, Class<T> expectedResultItemType) {
		
		Query q = this.getEm().createNamedQuery(namedQueryName);
		processQueryParameters(q, params);
		List<T> result = q.getResultList();
		return result;
	}
	
	private void processQueryParameters(Query query, Map<String, Object> params) {
		
		if (params != null) {
			for (String key : params.keySet()) {
				//System.out.println("setting param:" + key + " with value:" + params.get(key));
				query.setParameter(key, params.get(key));
			}
		}
		
	}
	
	
	public  List<?> search(SearchCriteria request){
		//TODO: ok, more configuration and IOC is needed here
		return  searchForList(request, Object.class);
	}
	
	public  <T> List<T> searchForList(SearchCriteria request, Class<T> classType){
		return  getSearchEngineFactory().engineFactory(request ).resultList(request, classType, getEm());
	}
	
	public  Object searchForObject(SearchCriteria request){
		return getSearchEngineFactory().engineFactory(request).singleResult(request, getEm());
	}
	
	public SearchEngineFactory getSearchEngineFactory(){
		return new SearchEngineFactory();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
