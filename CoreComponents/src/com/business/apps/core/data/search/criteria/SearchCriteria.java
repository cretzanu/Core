package com.business.apps.core.data.search.criteria;

import java.util.Set;

import eu.cec.digit.apps.meta.messages.Request;
/**
 * 
 * @author Liviu Gabriel Cretu
 *
 */
public interface SearchCriteria {

	Set<Predicate> getPredicateSet();
	

}