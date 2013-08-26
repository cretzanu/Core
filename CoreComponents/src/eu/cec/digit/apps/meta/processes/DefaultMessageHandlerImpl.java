package eu.cec.digit.apps.meta.processes;


import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.cec.digit.apps.meta.messages.AppMessage;
/**
 * To be extended by specific handlers.
 * @author cretuli
 *
 */
public abstract class DefaultMessageHandlerImpl implements  BusinessRulesProcessor{
	private static final String END_HANDLING_MESSAGE_PROCESSING_TOOK_MS = "=END {}. Work done in {} ms";
	private static final String START_HANDLING_MESSAGE = "=START {}";
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public Logger getLogger(){
		return logger;
	}
	
	/**
	 * callback for the App router.
	 */
	private Router appRouter;
	
	
	public Object execute(AppMessage message) {
		//do not add exception management here, unless you know what you are doing and the implications to EJB services
		long start=Calendar.getInstance().getTimeInMillis();
		getLogger().info(START_HANDLING_MESSAGE, message);
		
		this.restoreEntityContext(message);
		Object result = this.process(message);
		
		getLogger().info(END_HANDLING_MESSAGE_PROCESSING_TOOK_MS, message, Calendar.getInstance().getTimeInMillis()-start);
		return result;
		
	}
	
	/**
	 * The internals of the message processing
	 * @param message
	 * @return
	 */
//	protected abstract Object process(AppMessage message) ;
	
	protected  Object process(AppMessage message) {
		throw new RuntimeException("You have to implement this method!");
	}
	@Override
	public void postConditions(AppMessage message, Object processingResult,
			Router router) {
		//throw new RuntimeException(new OperationNotSupportedException("not implemented as it is not mandatory"));
		
	}
	@Override
	public void preConditions(AppMessage message, Router router) {
		//throw new RuntimeException(new OperationNotSupportedException("not implemented as it is not mandatory"));
		
	}
	
	

	
	protected void restoreEntityContext(AppMessage message){
		//replace any entities in the message with the EM merged version
	}

	

	public Router getAppRouter() {
		return appRouter;
	}

	public void setAppRouter(Router appRouter) {
		this.appRouter = appRouter;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	

	

}
