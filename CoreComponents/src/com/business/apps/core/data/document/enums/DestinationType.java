package com.business.apps.core.data.document.enums;


/**
 * 	@author batrian
 *	@version 1.00
 * 	@created 15-Nov-2012 15:04:47
 */
public enum DestinationType {
	PERSONAL_USE("PERSONAL_USE", "Personal use"),
	LIBRARY_USE("LIBRARY_USE", "Library use");
	
	private String key, value;
	
	private DestinationType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue(){
		return value;
	}
}
