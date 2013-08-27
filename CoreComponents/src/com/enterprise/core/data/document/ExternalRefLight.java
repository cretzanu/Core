package com.enterprise.core.data.document;

import java.io.Serializable;

public class ExternalRefLight implements Serializable{
	Long id;
	String description;
	String externalId;
	
	
	
	public ExternalRefLight() {
		super();
		
	}
	public ExternalRefLight(Long id,  String externalId, String description) {
		super();
		this.id = id;
		this.description = description;
		this.externalId = externalId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	
}
