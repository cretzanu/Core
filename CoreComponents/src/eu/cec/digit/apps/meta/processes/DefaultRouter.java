package eu.cec.digit.apps.meta.processes;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.cec.digit.apps.meta.messages.AppMessage;

public abstract class DefaultRouter extends DefaultRouterImpl implements MessageHandler, Router{
	
	private static final String HANDLER_FOUND_FOR_MESSAGE = "handler found for message {} : {}";
	private  Map<Integer, MessageHandler> handlers = new HashMap<Integer, MessageHandler>();
	private static Logger logger;//=LoggerFactory.getLogger(DefaultRouter.class);
//	static {
//		PropertyConfigurator.configure("e:/log4j.properties");
//		
//	}
	public Logger getLogger(){
		if (logger==null)
			{
			//this will take the config file, if any, from the root of this class jar
			PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
			logger=LoggerFactory.getLogger(this.getClass());
			}
		return logger;
	}
	
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.processes.Router#handleMessage(eu.cec.digit.apps.meta.messages.AppMessage)
	 */
	@Override
	public Object handleMessage(AppMessage message) {
		
		MessageHandler handler = getHandler(message);
		if (handler != null) {
			try {
				getLogger().info(HANDLER_FOUND_FOR_MESSAGE, message, handler);
				Object result= handler.handleMessage(message);
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
