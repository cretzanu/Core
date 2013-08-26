package com.business.apps.core.data.search.engine;

import com.business.apps.core.data.search.criteria.Predicate;
import com.business.apps.core.data.search.criteria.SearchEntityCriteria;
import com.business.apps.core.data.search.criteria.SearchCriteria;

public class SearchEntity extends SearchEngineBase {

	@Override
	protected StringBuilder getBaseQueryString(SearchCriteria request) {
		if (!(request instanceof SearchEntityCriteria))
			throw new IllegalArgumentException("I can only deal with"
					+ SearchEntityCriteria.class);

		StringBuilder sb = new StringBuilder().append("select ")
			.append(Predicate.DEFAULT_FIELD_PREFIX).append(" from ")
			.append(((SearchEntityCriteria) request).getEntityClass()
						.getSimpleName()).append(" ")
			.append(Predicate.DEFAULT_FIELD_PREFIX).append(" where 1=1 ");
		return sb;
	}

}
