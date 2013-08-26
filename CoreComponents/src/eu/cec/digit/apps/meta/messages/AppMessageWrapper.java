package eu.cec.digit.apps.meta.messages;
/**
 * Use this to deal with API messages (POJO messages without any dependencies exposed to the world)
 * @author Liviu Gabriel Cretu
 *
 */
public class AppMessageWrapper implements AppMessage {

	private Object message;

	public AppMessageWrapper(Object message) {
		super();
		this.message = message;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
}
