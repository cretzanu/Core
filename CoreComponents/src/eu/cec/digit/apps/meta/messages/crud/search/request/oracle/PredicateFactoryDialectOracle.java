package eu.cec.digit.apps.meta.messages.crud.search.request.oracle;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Date;

import com.business.apps.core.data.search.criteria.Operator;
import com.business.apps.core.data.search.criteria.Predicate;
import com.business.apps.core.data.search.criteria.PredicateFactory;

/**
 * By default, Date predicates will use the TRUNC function to remove the trailing time if needed.
 * @author Liviu Gabriel Cretu
 *
 */
public class PredicateFactoryDialectOracle extends PredicateFactory {

	@Override
	public Predicate predicateFactory(Class entityClass,
			String fieldName, Operator op, Object value) {

		try {
			PropertyDescriptor prop = new PropertyDescriptor(fieldName,
					entityClass);
			Predicate sc;
			if (Date.class.equals(prop.getPropertyType())){
				sc = new PredicateOracleDate(prop, op, value);
			} else if (String.class.equals(prop.getPropertyType())) {
				sc = new PredicateOracleString(prop, op, value);
			} else {
				sc = new Predicate(prop, op, value);
			}

			return sc;
		} catch (IntrospectionException e) {

			throw new IllegalArgumentException(
					"Cannot find property with name " + fieldName, e);
		}

	}
}