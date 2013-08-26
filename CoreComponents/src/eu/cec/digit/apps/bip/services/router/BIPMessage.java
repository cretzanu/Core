package eu.cec.digit.apps.bip.services.router;

import java.io.Serializable;
import java.util.List;
/**
 * Base interface for BIP messaging. For document messages (as opposed to event messages) it will indicate the return type of a processing request. 
 * @author Liviu Gabriel Cretu
 *
 * 
 * @param <R> - the return type
 */
public interface BIPMessage<R> extends Serializable {

		
	
}
