package com.enterprise.core.test.search;

import java.util.LinkedHashSet;
import java.util.Set;

import com.enterprise.core.data.PersistentEntity;
import com.enterprise.core.data.search.criteria.Predicate;
import com.enterprise.core.data.search.criteria.PredicateFactory;
import com.enterprise.core.data.search.criteria.SearchEntityCriteria;

/**
 * example of client side usage of the search component for easy mapping on Web pages
 * @author Liviu Gabriel Cretu
 *
 */
public class SearchDigitEntityRequest implements SearchEntityCriteria {
	//this should be some public constant in the app, servlet init param or it should be derived from Hibernate dialect
	public static PredicateFactory PREDICATE_FACTORY= new PredicateFactory(); 
	
	private Predicate dateUpdated  ;
	private Predicate user  ;
	
	public SearchDigitEntityRequest() {
		dateUpdated=PREDICATE_FACTORY.predicateFactory(this.getEntityClass(), "dateUpdated", null, null);
		user=PREDICATE_FACTORY.predicateFactory(this.getEntityClass(),"updatedByUser", null, null);
		}
	
	@Override
	public Class getEntityClass() {
		
		return PersistentEntity.class;
	}

	@Override
	public Set<Predicate> getPredicateSet() {
		Set<Predicate> p=new LinkedHashSet<Predicate>();
		p.add(dateUpdated);
		p.add(user);
		return p;
	}
	
	
	public Predicate getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Predicate dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Predicate getUser() {
		return user;
	}
	public void setUser(Predicate user) {
		this.user = user;
	}

	
	
	
	
}
