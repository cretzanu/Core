package com.business.apps.core.data.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;



/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */
@Entity
@NamedQueries ({
	@NamedQuery (name=ContactPersonDetail.REQUISITION_CONTACT_PERSON_DETAIL_READ_ALL_LIGHT, query="Select new eu.cec.digit.apps.meta.data.ExternalRefLight(d.id, d.externalId, d.description) from ContactPersonDetail d  ")
   ,@NamedQuery (name=ContactPersonDetail.READ_BY_EXTERNAL_ID, query="Select d from ContactPersonDetail d where d.externalId=:extId ")
})
public class ContactPersonDetail extends ExternalRef {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 435842037147467956L;

	public static final String REQUISITION_CONTACT_PERSON_DETAIL_READ_ALL_LIGHT = "requisition.ContactPersonDetail.readAllLight";
	public static final String READ_BY_EXTERNAL_ID = "requisition.ContactPersonDetail.readByExternalId";
	
	// Constants for field names
	public static final String CONTACT_REF			= "contactRef";
	public static final String FAX 					= "fax";
	public static final String FIRST_NAME 			= "firstName";
	public static final String LAST_NAME 			= "lastName";
	public static final String MAIL 				= "mail";
	public static final String PHONE 				= "phone";
	public static final String PLACE_OF_DELIVERY 	= "placeOfDelivery";
	public static final String BUILDING 			= "building";
	public static final String OFFICE 				= "office";
    public static final String ORGANISATION = "organization";
	
    @Transient
	private String contactRef;
	@Column(name="ETR_CPD_FAX")
	private String fax;
	@Column(name="ETR_CPD_FIRST_NM")
	private String firstName;
	@Column(name="ETR_CPD_LAST_NM")
	private String lastName;
	@Column(name="ETR_CPD_MAIL")
	private String mail;
	@Column(name="ETR_CPD_PHONE")
	private String phone;
	@Column(name="ETR_CPD_CITY_NM")
	private String placeOfDelivery;
	@Column(name="ETR_CDP_BUILDING_CD")
	private String building;
	@Column(name="ETR_CPD_OFFICE_CD")
	private String office;
	@Column(name="ETR_CPD_ORG_STRUCT_KEY_CD")
	private String organization;
	@Column(name="ETR_CPD_ECAS_USER_ID")
	private String userName;

	@PrePersist @PreUpdate
	void prePersist(){
		this.setDescription(this.firstName+" "+this.lastName+" "+this.mail);
	}
	
	public ContactPersonDetail(){

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuffer().append(super.toString()).append("[").append(firstName).append(lastName).append(mail).append("]").toString();
	}
	
	public void copy(ContactPersonDetail cpd){
		//todo - reflection
		this.building=cpd.building;
		this.fax=cpd.fax;
		this.firstName=cpd.firstName;
		this.lastName=cpd.lastName;
		this.mail=cpd.mail;
		this.office=cpd.office;
		this.organization=cpd.organization;
		this.phone=cpd.phone;
		this.placeOfDelivery=cpd.placeOfDelivery;
		this.userName=cpd.userName;
		this.setLastKnownRemoteUpdate(cpd.getLastKnownRemoteUpdate());
		this.setLastKnownRemoteUpdateVersion(cpd.getLastKnownRemoteUpdateVersion());
	}
	

	public ContactPersonDetail(Long id, String externalId, String description) {
		super(id, externalId, description);
		// TODO Auto-generated constructor stub
	}

	public String getContactRef() {
		return contactRef;
	}

	public void setContactRef(String contactRef) {
		this.contactRef = contactRef;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	
}