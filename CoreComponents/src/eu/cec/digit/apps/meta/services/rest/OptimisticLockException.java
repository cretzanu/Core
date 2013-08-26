package eu.cec.digit.apps.meta.services.rest;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * '400' : "Server understood the request but optimistic lock exception found."
 * 
 * @author Liviu Gabriel Cretu
 * 
 */
public class OptimisticLockException extends WebApplicationException {
	// key=field name, value= error message key
	Map<String, String> errors;
/**
 * key=field name, value= error message key
 * @param errors
 */
	public OptimisticLockException(Map<String, String> errors) {
		
		super(Response.status(400).entity(errors).type(
				"application/json; charset=utf-8").build());
		System.out.println("========"+this.getClass().getSimpleName() + " errors:"+errors.keySet().size());

	}

}
