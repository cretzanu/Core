package eu.cec.digit.apps.meta.generics.processes;

import eu.cec.digit.apps.meta.messages.AppMessage;

public interface GenericRouter {
	
	public <R> R handleMessage(AppMessage<R> request);

}
