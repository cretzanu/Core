package com.enterprise.core.data.document.enums;
/**
 * constants represeting key-names when the enums are used in URI params. e.g.:
 * http://127.0.0.1:7001/digit-customer-portal/requisition/rest/main/menu?dossierType=PROVISION_OF_GOODS&domainType=LIBRARY&serviceReferenceType=REQUISITION&serviceProvider=%5C%5CEU%5CCE%5CDGT
 * 
 * @author cretuli
 *
 */
public class EnumParamsIdentifiers {
	
	public static final String SERVICE_PROVIDER = "serviceProvider";
	public static final String SERVICE_REFERENCE_TYPE = "serviceReferenceType";
	public static final String DOMAIN_TYPE = "domainType";
	public static final String DOSSIER_TYPE = "dossierType";

}
