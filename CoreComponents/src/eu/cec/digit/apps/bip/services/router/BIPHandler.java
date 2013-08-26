package eu.cec.digit.apps.bip.services.router;

public interface BIPHandler {
	public <R> R handleMessage( BIPMessage<R> request);
}
