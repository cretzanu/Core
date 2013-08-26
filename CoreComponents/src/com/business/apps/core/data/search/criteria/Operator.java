package com.business.apps.core.data.search.criteria;

public enum Operator {
	EQUALS_TO,
	GREATER_THAN_OR_EQUALS_TO,
	LESS_THEN_OR_EQUALS_TO,
	LESS_THEN,
	GREATER_THEN,
	LIKE,
	IS_NULL,
	IN,
	IS,
	IS_NOT,
	BETWEEN//used to simulate Between operator with 2 operands
	;
	
	/**
	 * used internally, do not modify to adapt to GUI 
	 * @see Predicate
	 */
	public String toString(){
		switch(this) {
		case EQUALS_TO : return "=";
		case GREATER_THAN_OR_EQUALS_TO : return ">=";
		case LESS_THEN_OR_EQUALS_TO : return "<=";
		case LESS_THEN : return "<";
		case GREATER_THEN : return ">";
		case LIKE : return " LIKE ";
		case IS_NULL : return " IS NULL ";
		case IN : return " IN ";
		case IS : return " = ";
		case IS_NOT : return " IS NOT ";
		case BETWEEN : return " >= ";
		default : return "N/A"; 
		}
	}
	
	/**
	 * needed for JSON 
	 * @return
	 */
	public String toNaturalString(){
		return super.toString();
	}
	/**
	 * not used with the new JSON approach of Predicates in GUI
	 */
	public static Operator fromString(String s){
		
		if ("=".equals(s)) return EQUALS_TO;
		if (">=".equals(s)) return  GREATER_THAN_OR_EQUALS_TO ;
		if ("<=".equals(s)) return  LESS_THEN_OR_EQUALS_TO ;
		if ("<".equals(s)) return  LESS_THEN ;
		if (">".equals(s)) return  GREATER_THEN ;
		if (" LIKE ".equals(s)) return  LIKE ;
		if (" IS NULL ".equals(s)) return  IS_NULL ;
		if (" IN ".equals(s)) return  IN ;
		
	return null; 
		}
        
        public String toENLabelString(String lang){
		switch(this) {
		case EQUALS_TO : return "=";
		case GREATER_THAN_OR_EQUALS_TO : return ">=";
		case LESS_THEN_OR_EQUALS_TO : return "<=";
		case LESS_THEN : return "<";
		case GREATER_THEN : return ">";
		case LIKE : return " CONTAINS ";
		case IS_NULL : return " IS NULL ";
		case IN : return " IN ";
		case IS : return " = ";
		case IS_NOT : return " IS NOT ";
		case BETWEEN : return " >= ";
		default : return "N/A"; 
		}
	}
        
	}
	

