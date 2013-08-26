package eu.cec.digit.apps.meta.services.rest;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

/**
 * '400' : "Server understood the request but request content was invalid."
 * 
 * @author Liviu Gabriel Cretu
 * 
 */
public class ValidationException extends WebApplicationException {
	// key=field name, value= error message key
	Map<String, String> errors;
/**
 * key=field name, value= error message key
 * @param errors
 */
	public ValidationException(Map<String, String> errors) {
		
		super(Response.status(400).entity(errors).type(
				"application/json; charset=utf-8").build());
		for (Map.Entry entry : errors.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		System.out.println("========"+this.getClass().getSimpleName() + " errors:"+errors.keySet().size());

	}

}
