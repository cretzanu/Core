package eu.cec.digit.apps.meta.processes.rules;

import eu.cec.digit.apps.meta.messages.AppMessage;


public interface  MessageValidator<M extends AppMessage,C> {

	public void validate(MessageContext<M,C> message) throws MessageValidationException;
}
