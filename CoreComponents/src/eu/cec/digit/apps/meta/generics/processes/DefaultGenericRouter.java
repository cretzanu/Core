package eu.cec.digit.apps.meta.generics.processes;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.cec.digit.apps.meta.messages.AppMessage;
import eu.cec.digit.apps.meta.processes.DefaultRouter;
import eu.cec.digit.apps.meta.processes.DefaultRouterImpl;
import eu.cec.digit.apps.meta.processes.MessageHandler;

public class DefaultGenericRouter extends DefaultRouterImpl implements GenericRouter{
	private static final String HANDLER_FOUND_FOR_MESSAGE = "handler found for message {} : {}";
	private  Map<Integer, MessageHandler> handlers = new HashMap<Integer, MessageHandler>();
	private static Logger logger=LoggerFactory.getLogger(DefaultRouter.class);
	public Logger getLogger(){
		return logger;
	}
	
	public <R> R handleMessage(AppMessage<R> message){	
		
		GenericMessageHandler<AppMessage<R>, R> handler = getGenericHandler(message);
		if (handler != null) {
			try {
				getLogger().info(HANDLER_FOUND_FOR_MESSAGE, message, handler);
				R result= handler.handleMessage(message);
				postConditions(message, handler, result);
				return result;
			} catch (RuntimeException e) {
				getLogger().error(e.getMessage(), e);
				throw e;
			}
			
		} else
			throw new IllegalArgumentException("No handler found for message:"
					+ message.getClass());
	
	}
}
