package com.enterprise.core.data.search.criteria;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class PredicateFactory {

	public Predicate predicateFactory(Class entityClass, String fieldName) {
		return this.predicateFactory(entityClass, fieldName, null, null);
	}
	/**
	 *  Defaults to Operator.QUALS_TO
	 * @param entityClass
	 * @param fieldName
	 * @param alias
	 * @return
	 */
	public Predicate predicateFactory(Class entityClass, String fieldName, String alias) {
		Predicate p= this.predicateFactory(entityClass, fieldName, Operator.EQUALS_TO, null);
		p.setPrefix(alias);
		return p;
	}

	/**
	 * Allows the choice of the default operator.
	 * @param entityClass
	 * @param fieldName
	 * @param alias
	 * @param defaultOperator
	 * @return
	 */
	/*public Predicate predicateFactory(Class entityClass, String fieldName, Operator defaultOperator, String alias, Object value) {
		Predicate p= this.predicateFactory(entityClass, fieldName, defaultOperator, value);
		p.setPrefix(alias);
		return p;
	}*/

	public Predicate predicateFactory(Class entityClass, String fieldName,
			Operator op, Object value) {

		try {
			PropertyDescriptor prop = new PropertyDescriptor(fieldName,
					entityClass);
			Predicate sc = new Predicate(prop, op, value);

			return sc;
		} catch (IntrospectionException e) {

			throw new IllegalArgumentException(
					"Cannot find property with name " + fieldName, e);
		}

	}


}