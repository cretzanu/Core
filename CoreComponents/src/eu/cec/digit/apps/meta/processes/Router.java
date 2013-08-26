package eu.cec.digit.apps.meta.processes;

import eu.cec.digit.apps.meta.messages.AppMessage;

public interface Router {

	public abstract Object handleMessage(AppMessage message);

	/**
	 * creates a new POJO instance of the handler class
	 * @param messageClass
	 * @param handlerClass
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract void registerHandler(Class messageClass, Class handlerClass)
			throws InstantiationException, IllegalAccessException;

	/**
	 * you may add even an EJB handler with this... :)
	 * @param messageClass
	 * @param handler
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract void registerHandler(Class messageClass,
			MessageHandler handler);

}