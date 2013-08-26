package eu.cec.digit.apps.meta.services.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ProvidedServiceUnknownException extends WebApplicationException{

	public ProvidedServiceUnknownException() {
		super(new Exception("Provided service cannot be identified. Please check the URI parameters needed to access this service."));
	}

	
}
