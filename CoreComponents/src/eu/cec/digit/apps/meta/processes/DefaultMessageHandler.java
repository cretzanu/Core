package eu.cec.digit.apps.meta.processes;


import java.util.Calendar;

import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.cec.digit.apps.meta.messages.AppMessage;

public abstract class DefaultMessageHandler extends DefaultMessageHandlerImpl implements MessageHandler, BusinessRulesProcessor{
	protected static final String HANDLE_METHOD = "handle";
	/**
	 * default implementation
	 */
	@Override
	public Object handleMessage(AppMessage message) {
		return super.execute(message);
		
	}
	
	protected abstract Object process(AppMessage message) ;
}
