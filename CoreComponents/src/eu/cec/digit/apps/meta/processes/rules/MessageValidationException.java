package eu.cec.digit.apps.meta.processes.rules;

import java.util.HashMap;
import java.util.Map;

public class MessageValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, String> errors=new HashMap<String,String>();
	
	/**
	 * key=field name, value= error message key
	 * @param errors
	 */
	public MessageValidationException(Map<String, String> errors) {
			this.errors=errors;
	}
	public MessageValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageValidationException(String message, Throwable cause) {
		super(message, cause);
		this.errors.put(cause.getClass().getSimpleName(), message);
	}

	public MessageValidationException(String message) {
		super(message);
		this.errors.put(this.getClass().getSimpleName(), message);
	}

	public MessageValidationException(Throwable cause) {
		super(cause);
		this.errors.put(cause.getClass().getSimpleName(), cause.getMessage()); 
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		if (errors==null)
			return super.getMessage();
		else
			return super.getMessage() + errors.toString();
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	

}
