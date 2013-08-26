package eu.cec.digit.apps.meta.generics.processes;


/**
 * Base interface for Document message (requests) handler indicating the return type.
 * @author cretuli
 *
 * @param <M>
 * @param <R>
 */
public interface  GenericMessageHandler<M, R> {
	public R handleMessage( M message);
}
