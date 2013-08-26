package com.business.apps.core.data.search.criteria;

import java.beans.PropertyDescriptor;
import java.io.Serializable;

import javax.persistence.Query;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")//http://www.cowtowncoder.com/blog/archives/2010/03/entry_372.html
public class Predicate implements Serializable {
	public static Logger logger=LoggerFactory.getLogger(Predicate.class);
	
	public static final String DEFAULT_FIELD_PREFIX = "t";
	private static final long serialVersionUID = -4060484141734017797L;
	private static final String POINTS = ":";
	private static final String STRING = "}";
	private static final String SEMICOLON = ";";
	private static final String BRACK = "{";
	private static final String PERCENTAGE_SIGN	= "%";
	
	private String fieldName;
	private String paramName;  
	
	
	//private transient PropertyDescriptor field;//not serializable- see the getter
	private Operator operator=Operator.EQUALS_TO;
	private LogicalOperator logicalOperator = LogicalOperator.AND;
	private Object value;
	private String prefix=DEFAULT_FIELD_PREFIX;

	public Predicate(){}
	public Predicate(PropertyDescriptor prop, Operator operator,
			Object value) {
		// TODO: check value type
		super();
		//this.setField (field);
		this.fieldName=prop.getName();
		this.operator = operator;
		this.value = value;
	}

	public Predicate(LogicalOperator logicalOperator,
			PropertyDescriptor field, Operator operator, Object value) {
		this(field, operator, value);
		this.logicalOperator = logicalOperator;

	}

	public String buildQueryParam() {
		return this.logicalOperator.toString() + this.getOperand1()
		+ this.operator + POINTS + this.getParamName();
	

	}

	public void setQueryParam(Query q) {
		logger.debug("param {}:{}", this.getParamName(), this.getCorrectedValue());
		q.setParameter(this.getParamName(), this.getCorrectedValue());
	}

	/**
	 * override this for special cases like applying functions to certain field
	 * types
	 * 
	 * @return
	 */
	protected String getOperand1() {
		return this.prefix+"."+this.fieldName;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(super.toString()).append(BRACK)
				.append(logicalOperator).append(fieldName).append(SEMICOLON)
				.append(operator != null ? operator.toString() : "null").append(value).append(STRING)
				.toString();
	}

	public String getParamName() {
		if (this.paramName == null){
			return this.fieldName;
		} else {
			return this.paramName;
		}
		
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

//	public PropertyDescriptor getField() {
//		return field;
//	}
//
//	public void setField(PropertyDescriptor field) {
//		this.field = field;
//		
//	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

//	@Ignore
	protected Object getCorrectedValue(){
		Object correctedValue = getValue();
		if (getOperator().equals(Operator.LIKE) && correctedValue != null && correctedValue.getClass().equals(String.class)){
			correctedValue = PERCENTAGE_SIGN + correctedValue + PERCENTAGE_SIGN;
			System.err.println("corrected value for " + getFieldName() + ": " + correctedValue);
		}
		return correctedValue;
	}
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getFieldName() {
		return fieldName;
	}
	

	
}
