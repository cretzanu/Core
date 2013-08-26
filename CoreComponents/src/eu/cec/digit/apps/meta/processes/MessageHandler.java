package eu.cec.digit.apps.meta.processes;

import eu.cec.digit.apps.meta.messages.AppMessage;

public interface MessageHandler {

	Object handleMessage(AppMessage message);
}
