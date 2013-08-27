package com.enterprise.core.data.search.engine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.enterprise.core.data.search.criteria.Operator;
import com.enterprise.core.data.search.criteria.Predicate;
import com.enterprise.core.data.search.criteria.SearchCriteria;


public abstract class SearchEngineBase implements SearchEngine{

	@Override
	public List<?> resultList(SearchCriteria request, EntityManager em) {
		return resultList(request, Object.class, em);
	}
	
	@Override
	public <T> List<T> resultList(SearchCriteria request, Class<T> returnClassType, EntityManager em) {
		// Query should be replaced by a TypedQuery when changing the persistence to version 2.0
		Query q = build(request, em);
		@SuppressWarnings("unchecked")
		List<T> result = q.getResultList();
		return result;
		
	}

	protected Query build(SearchCriteria request, EntityManager em) {
		String query = this.buildQueryString(request);
		System.out.println("query=" + query);
		Query q=em.createQuery(this.buildQueryString(request));
		this.setParams(q, request);
		return q;
	}

	@Override
	public Object singleResult(SearchCriteria request, EntityManager em) {
		return singleResult(request, Object.class, em);
	}
	
	@Override
	public <T> T singleResult(SearchCriteria request, Class<T> returnClassType, EntityManager em) {
		// Query should be replaced by a TypedQuery when changing the persistence to version 2.0
		Query q = build(request, em);
		@SuppressWarnings("unchecked")
		T obj = (T) q.getSingleResult();
		return obj;
	}
	
	public String buildQueryString(SearchCriteria request) {
		
		if (request.getPredicateSet().isEmpty())
			throw new RuntimeException(
					"Predicate list is empty... Use the usual ReadAll instead ");
		StringBuilder sb = getBaseQueryString(request);
		return buildWhereClause(request, sb);
	}
	
	protected String buildWhereClause(SearchCriteria request, StringBuilder sb) {
		for (Predicate p : request.getPredicateSet()) {
			if (!this.ignorePredicate(p)) {
				sb.append(p.buildQueryParam());
			}
		}
		return sb.toString();
	}
	
	protected void setParams(Query q, SearchCriteria request) {
		for (Predicate p : request.getPredicateSet()) {
			if (!this.ignorePredicate(p))
				p.setQueryParam(q);
		}
	}
	
	protected abstract StringBuilder getBaseQueryString(SearchCriteria request) ;
	
	/**
	 * null predicate values are ignored and empty strings
	 * 
	 * @return
	 */
	protected boolean ignorePredicate(Predicate p) {
		Boolean isIgnored = false;
		Object predicateValue = p.getValue();
		if (isNullPredicateValue(p) || isEmptyPredicateValue(predicateValue)){
			isIgnored = true;
		} else {
		}
		return isIgnored;
	}

	protected boolean isNullPredicateValue(Predicate p){
		return p.getValue() == null && Operator.IS_NULL != p.getOperator();
	}
	
	protected boolean isEmptyPredicateValue(Object o){
		return o.getClass().equals(String.class) && ((String)o).trim().isEmpty();
	}
}
