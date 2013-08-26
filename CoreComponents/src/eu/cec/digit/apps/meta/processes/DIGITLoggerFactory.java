package eu.cec.digit.apps.meta.processes;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DIGITLoggerFactory {
	
	private static final String LOG4J_PROPERTIES = "log4j.properties";

	public static Logger getLogger(Class clazz){
		try{
			PropertyConfigurator.configure(clazz.getClassLoader().getResource(LOG4J_PROPERTIES));
		}catch(Throwable t){
			t.printStackTrace();
		}
		return LoggerFactory.getLogger(clazz);
	}
	public static Logger getLogger(Class clazz, String propertyFileName){
		try{
			PropertyConfigurator.configure(clazz.getClassLoader().getResource(propertyFileName));
		}catch(Throwable t){
			t.printStackTrace();
		}
		return LoggerFactory.getLogger(clazz);
	}
}
