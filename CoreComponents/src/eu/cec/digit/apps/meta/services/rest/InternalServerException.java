package eu.cec.digit.apps.meta.services.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class InternalServerException extends WebApplicationException{
	
	@SuppressWarnings("unchecked")
	public InternalServerException(Throwable t){
		super(Response.status(Status.INTERNAL_SERVER_ERROR)
					  .entity(new InternalServerExceptionBuilder().buildResponseEntity(t))
					  .type("application/json; charset=utf-8").build());
		//System.out.println(t.getCause());
		t.printStackTrace();
	}
	
}

class InternalServerExceptionBuilder {
	private Map errorResponse=new HashMap();
	
	@SuppressWarnings("unchecked")
	public Map buildResponseEntity(Throwable t){
		errorResponse.put("type", t.getClass().getSimpleName());
		errorResponse.put("cause", t.getCause()!=null ? t.getCause().getClass().getSimpleName() : null);
		errorResponse.put("message", t.getMessage());
		return errorResponse;
	}
}
