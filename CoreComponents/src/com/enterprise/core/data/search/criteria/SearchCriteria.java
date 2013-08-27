package com.enterprise.core.data.search.criteria;

import java.util.Set;
/**
 * 
 * @author Liviu Gabriel Cretu
 *
 */
public interface SearchCriteria {

	Set<Predicate> getPredicateSet();
	

}