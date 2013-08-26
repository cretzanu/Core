package com.business.apps.core.data.search.engine;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.business.apps.core.data.search.criteria.SearchNamedQueryCriteria;
import com.business.apps.core.data.search.criteria.SearchCriteria;


public class SearchEngineByNamedQuery extends SearchEngineBase{

	@Override
	protected Query build(SearchCriteria request, EntityManager em) {
		Query q=em.createQuery(this.buildQueryString(request, em));
		this.setParams(q, request);
		return q;
	}
	
	public String buildQueryString(SearchCriteria request, EntityManager em) {
		
		if (request.getPredicateSet().isEmpty())
			throw new RuntimeException(
					"Predicate list is empty... Use the common ReadAll instead ");
		StringBuilder sb = getBaseQueryString(request, em);
		return buildWhereClause(request, sb);
	}

	
	protected StringBuilder getBaseQueryString(SearchCriteria request){
		return null;
	}
	
	protected StringBuilder getBaseQueryString(SearchCriteria request,  EntityManager em) {
		
		if (!(request instanceof SearchNamedQueryCriteria))
			throw new IllegalArgumentException("I can only deal with"
					+ SearchNamedQueryCriteria.class);
		SearchNamedQueryCriteria snqr=(SearchNamedQueryCriteria)request;
		org.hibernate.ejb.HibernateQuery hbQuery=((org.hibernate.ejb.HibernateQuery)em.createNamedQuery(snqr.getNamedQuery()));
		String queryString=hbQuery.getHibernateQuery().getQueryString();
		
		return new StringBuilder(queryString);
	}
	

}
