package eu.cec.digit.apps.meta.messages.crud.search.request.oracle;

import java.beans.PropertyDescriptor;
import java.util.Date;

import com.business.apps.core.data.search.criteria.LogicalOperator;
import com.business.apps.core.data.search.criteria.Operator;
import com.business.apps.core.data.search.criteria.Predicate;



/**
 * the first operand generated by this criteria will look like : TRUNC(field)
 * instead of field
 * 
 * @author Liviu Gabriel Cretu
 * 
 */
public class PredicateOracleDate extends Predicate {

	private static final String P = ")";
	private static final String TRUNC = "TRUNC(";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5424229416280726462L;
	public PredicateOracleDate(){}
	public PredicateOracleDate(LogicalOperator logicalOperator,
			PropertyDescriptor field, Operator operator, Object value) {
		super(logicalOperator, field, operator, value);
		if (!checkPropertyDate(field))
			throw new IllegalArgumentException("This one is intended for Dates only!");
	}

	private boolean checkPropertyDate(PropertyDescriptor field) {
		return Date.class.equals(field.getPropertyType());
		
	}

	public PredicateOracleDate(PropertyDescriptor field,
			Operator operator, Object value) {
		super(field, operator, value);
		if (!checkPropertyDate(field))
			throw new IllegalArgumentException("This one is intended for Dates only!");
	}

	@Override
	protected String getOperand1() {
		// TODO Auto-generated method stub
		return TRUNC+super.getOperand1()+P;
	}

}