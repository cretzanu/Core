package eu.cec.digit.apps.meta.messages;

import java.util.Date;

/**
 * Every message implementing this interface will enrich internal entities with system info
 * (e.g. user, date created etc) provided BY THE System, not by the user (user-defined will be replaced).
 * 
 * @author Liviu Gabriel Cretu
 * 
 */
public interface AddSystemInfoToEntities {

	void updateEntities(String user, Date date);
}
