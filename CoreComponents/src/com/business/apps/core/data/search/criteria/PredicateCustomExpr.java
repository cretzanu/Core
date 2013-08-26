package com.business.apps.core.data.search.criteria;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

public class PredicateCustomExpr extends Predicate {
	
	private Map<String,Object> values;
	private String operandExpr;
	
	public PredicateCustomExpr(String operandExpr ) {
		super();
	
		this.operandExpr=operandExpr;
	}

	@Override
	public Object getValue() {
		// Need this for the engine to take it into account!!!
		return values!=null;
	}
	public String buildQueryParam() {
		return this.getLogicalOperator().toString() + this.operandExpr;
		 
	

	}

	public void setQueryParam(Query q) {
		
		Iterator<Entry<String, Object>> it = values.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<String, Object> pairs = it.next();
	        logger.debug("param {}:{}",pairs.getKey(),  pairs.getValue());
	        q.setParameter(pairs.getKey(), pairs.getValue());    
	    }
		
	}
	
	
	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	public String getOperandExpr() {
		return operandExpr;
	}

	public void setOperandExpr(String operandExpr) {
		this.operandExpr = operandExpr;
	}
	
	

}
