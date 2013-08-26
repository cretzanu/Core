package eu.cec.digit.apps.meta.processes;

import java.io.Serializable;

import eu.cec.digit.apps.meta.messages.AppMessage;
/**
 * Just subclass this one in each DIGIT App in order to have a distinct type per App. 
 * @author Liviu Gabriel Cretu
 *
 */
public class BaseHandlerException extends RuntimeException implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8787309802595263645L;
	AppMessage errorSource;

	public BaseHandlerException(String message, Throwable cause,
			AppMessage errorSource) {
		super(message, cause);
		
		//cause.printStackTrace();
		this.errorSource = errorSource;
	}

	public BaseHandlerException(String message, AppMessage errorSource) {
		super(message);
		this.errorSource = errorSource;

	}

	public BaseHandlerException(Throwable cause, AppMessage errorSource) {
		super(cause);

		//cause.printStackTrace();
		this.errorSource = errorSource;
	}
	@Override
	public String getMessage() {

		if (errorSource == null)
			return super.getMessage();
		
		return new StringBuilder().append("Error processing message:").append(
				errorSource.getClass()).append("::").append(
				errorSource.toString()).append("\n").append(
				"Original Exception message:").append(super.getMessage()).toString();
	}

	public AppMessage getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(AppMessage errorSource) {
		this.errorSource = errorSource;
	}

}
