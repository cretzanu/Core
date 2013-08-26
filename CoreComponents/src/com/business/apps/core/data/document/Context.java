package com.business.apps.core.data.document;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.business.apps.core.data.document.enums.DomainType;
import com.business.apps.core.data.document.enums.DossierType;
import com.business.apps.core.data.document.enums.ServiceReferenceType;


/**
 * Fields overriden on subclass level
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */
@Embeddable
public class Context implements Serializable {
	//fields used in search predicates
	public static final String DOSSIER_TYPE 	= "dossierType";
	public static final String REQUESTING_ORG 	= "requestingOrg";
	public static final String WRKFLW_STP 		= "currentWorkflowStep";
	
	

	//** END query needed constants**//
	
	@Enumerated(EnumType.STRING)
	private DossierType dossierType=DossierType.PROVISION_OF_GOODS;
	@Enumerated(EnumType.STRING)
	private DomainType domainType=DomainType.LIBRARY;
	private String workflowRef;
	
	 //let this be Enums free if you want the flexibility of the WF definition independently of all the services
	private String currentWorkflowStep;
	private String requestingOrg;
	private String serviceProvider;
	@Enumerated(EnumType.STRING)
	private ServiceReferenceType serviceReference;
	@Transient
	private String userId;
	@Transient
	private String ISO2LanguageCode;
	

	public Context(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public DossierType getDossierType(){
		return dossierType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDossierType(DossierType newVal){
		dossierType = newVal;
	}

	public DomainType getDomainType(){
		return domainType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDomainType(DomainType newVal){
		domainType = newVal;
	}

	public String getWorkflowRef(){
		return workflowRef;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setWorkflowRef(String newVal){
		workflowRef = newVal;
	}

	public String getCurrentWorkflowStep(){
		return currentWorkflowStep;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCurrentWorkflowStep(String newVal){
		currentWorkflowStep = newVal;
	}

	public String getRequestingOrg(){
		return requestingOrg;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRequestingOrg(String newVal){
		requestingOrg = newVal;
	}

	public String getServiceProvider(){
		return serviceProvider;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setServiceProvider(String newVal){
		serviceProvider = newVal;
	}

	public ServiceReferenceType getServiceReference(){
		return serviceReference;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setServiceReference(ServiceReferenceType newVal){
		serviceReference = newVal;
	}

	public String getUserId(){
		return userId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUserId(String newVal){
		userId = newVal;
	}

	public String getISO2LanguageCode(){
		return ISO2LanguageCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setISO2LanguageCode(String newVal){
		ISO2LanguageCode = newVal;
	}

	
}//end Context