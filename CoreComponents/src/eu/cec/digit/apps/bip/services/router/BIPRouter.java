package eu.cec.digit.apps.bip.services.router;


/**
 * Facade to be exposed using various technologies to the remote clients
 * @author Liviu Gabriel Cretu
 *
 */
public interface BIPRouter extends BIPHandler{
	
	public static final String BIPROUTER_EJB_NAME = "BIPRouterEJB#eu.cec.digit.apps.bip.services.router.BIPRouter";
	
	
	public Object handleMessage(Object message);

	
}
