package eu.cec.digit.apps.meta.processes;

import eu.cec.digit.apps.meta.messages.AppMessage;
/**
 * A concrete implementation knows how to deal with business rules. 
 * @author Liviu Gabriel Cretu
 *
 */
public interface BusinessRulesProcessor {

	public void preConditions(AppMessage message, Router router);
	public void postConditions(AppMessage message, Object processingResult, Router router);
}
