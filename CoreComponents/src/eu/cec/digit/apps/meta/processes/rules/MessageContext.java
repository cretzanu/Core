package eu.cec.digit.apps.meta.processes.rules;

import eu.cec.digit.apps.meta.messages.AppMessage;

public class MessageContext<M extends AppMessage, C> {
	
	private M message;
	private C context;
	
	public MessageContext(M message, C context) {
		super();
		this.message = message;
		this.context = context;
	}

	public M getMessage(){
		return message;
	}

	public C getContext(){
		return context;
	}
}
