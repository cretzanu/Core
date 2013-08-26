package eu.cec.digit.apps.meta.generics.messages.crud;

import java.util.Map;

import eu.cec.digit.apps.meta.messages.Request;

public class ReadByNamedQueryMsg<R> implements Request<R> {
	private String queryName;
	private Map<String, Object> params;
	
	public ReadByNamedQueryMsg(String queryName, Map<String, Object> params) {
		super();
		this.queryName = queryName;
		this.params = params;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	
}
