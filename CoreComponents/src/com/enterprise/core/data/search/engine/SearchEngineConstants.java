package com.enterprise.core.data.search.engine;

import com.enterprise.core.data.search.criteria.PredicateFactory;

public class SearchEngineConstants {
//	/**
//	 * Does not use any specific dialect
//	 */
	// public static PredicateFactory SEARCH_PREDICATE_FACTORY= new
	// PredicateFactory();//use it for no specific dialect
	/**
	 * This one uses Oracle dialect. 
	 * Using oracle Dialect means, by default, predicate queries TRUNCATE Date type fields
	 * to drop time fields (critical implications on the results obtained throughout 
	 * different hours of the day)
	 */
	public static PredicateFactory SEARCH_PREDICATE_FACTORY = null;//new PredicateFactoryDialectOracle(); // use
																									// it
																									// for
																									// Oracle
																									// Dialect

}
