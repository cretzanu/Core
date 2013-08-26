package eu.cec.digit.apps.meta.processes;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.cec.digit.apps.meta.generics.messages.crud.ReadByNamedQueryMsg;
import eu.cec.digit.apps.meta.generics.processes.DefaultGenericMessagehandler;
import eu.cec.digit.apps.meta.generics.processes.DefaultGenericRouter;
import eu.cec.digit.apps.meta.generics.processes.GenericMessageHandler;
import eu.cec.digit.apps.meta.messages.AppMessage;
import eu.cec.digit.apps.meta.messages.AppMessageWrapper;

public abstract class DefaultRouterImpl {
	private static final String HANDLER_NOT_FOUND_ = "Handler NOT found for message {}. Trying to identify default handler by naming convention...";
	
	private  Map<Integer, MessageHandler> handlers = new HashMap<Integer, MessageHandler>();
	private  Map<Integer, GenericMessageHandler> genericHandlers = new HashMap<Integer, GenericMessageHandler>();
	private static Logger logger=LoggerFactory.getLogger(DefaultRouterImpl.class);
	public Logger getLogger(){
		return logger;
	}
	
	protected GenericMessageHandler getGenericHandler(AppMessage message) {
		int id =0;
		if (message instanceof AppMessageWrapper)
			id=((AppMessageWrapper)message).getMessage().hashCode();
		else
			id = message.getClass().hashCode();
		GenericMessageHandler handler = this.genericHandlers.get(id);
		if (handler == null) {
			getLogger().info(HANDLER_NOT_FOUND_, message, handler);
			// by default this is using naming by convention for the handler class
			try {
				handler = (GenericMessageHandler) Class.forName(
						message.getClass().getPackage().getName() + '.'
								+ message.getClass().getSimpleName() + "Handler")
						.newInstance();
				genericHandlers.put(id, handler);
			} catch (Throwable e) {
				getLogger().error(e.getMessage(), e);
			}
		}
		return handler;
	}
	

	protected MessageHandler getHandler(AppMessage message) {
		int id =0;
		if (message instanceof AppMessageWrapper)
			id=((AppMessageWrapper)message).getMessage().hashCode();
		else
			id = message.getClass().hashCode();
		MessageHandler handler = this.handlers.get(id);
		if (handler == null) {
			getLogger().info(HANDLER_NOT_FOUND_, message, handler);
			// by default this is using naming by convention for the handler class
			try {
				handler = (MessageHandler) Class.forName(
						message.getClass().getPackage().getName() + '.'
								+ message.getClass().getSimpleName() + "Handler")
						.newInstance();
				handlers.put(id, handler);
			} catch (Throwable e) {
				getLogger().error(e.getMessage(), e);
			}
		}
		return handler;
	}
	/**
	 * Override as needed.Example: use this operation to send events to BIP 
	 * @param message
	 * @param handler
	 * @param result
	 */
	
	public void postConditions(AppMessage message, MessageHandler handler, Object result) {
		//not abstract --> no MUST for the concrete class
	}
	
	/**
	 * Override as needed.Example: use this operation to send events to BIP 
	 * @param message
	 * @param handler
	 * @param result
	 */
	
	public void postConditions(AppMessage message, GenericMessageHandler handler, Object result) {
		//not abstract --> no MUST for the concrete class
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.processes.Router#registerHandler(java.lang.Class, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public void registerHandler(Class messageClass, Class handlerClass) throws InstantiationException, IllegalAccessException{
		this.handlers.put(messageClass.hashCode(), (MessageHandler) handlerClass.newInstance());
	}
	
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.processes.Router#registerHandler(java.lang.Class, eu.cec.digit.apps.meta.processes.MessageHandler)
	 */
	@SuppressWarnings("rawtypes")
	public void registerHandler(Class messageClass, MessageHandler handler) {
		this.handlers.put(messageClass.hashCode(), handler);
	}
	
	public void registerHandler(Class messageClass, GenericMessageHandler handler) {
		this.genericHandlers.put(messageClass.hashCode(), handler);
	}

	
}
