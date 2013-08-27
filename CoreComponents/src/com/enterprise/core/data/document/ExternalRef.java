package com.enterprise.core.data.document;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.enterprise.core.data.PersistentEntity;

/**
 * References to external data used by some App. Needed for cross-domains inter-operability. 
 * Example: MyDigitApp.MyClass.externalRefOrder-->ABACAssets.Order
 * 
 * Each new remote ref requested by the user will be stored locally as an object of this type (subtype)
 * @author Liviu Gabriel Cretu
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(      name="ETR_TYPE_CD", discriminatorType=DiscriminatorType.STRING )
@Table (name="REQ_TB_EXTERNALREF")
@AttributeOverrides( {
	 @AttributeOverride(name = "id", column = @Column(name = "ETR_ID"))
	 ,@AttributeOverride(name = "version", column = @Column(name = "ETR_VERSION_NO"))
	 ,@AttributeOverride(name = "createdByUser", column = @Column(name = "ETR_CRE_ID"))
	 ,@AttributeOverride(name = "updatedByUser", column = @Column(name = "ETR_MOD_ID"))
	 ,@AttributeOverride(name = "dateCreated", column = @Column(name = "ETR_CRE_DT"))
	 ,@AttributeOverride(name = "dateUpdated", column = @Column(name = "ETR_MOD_DT"))
	 ,@AttributeOverride(name = "entity_type", column = @Column(name = "ETR_ENTITY_TYPE"))
	 
   } )
   
public class ExternalRef extends PersistentEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3046407970465622226L;
	/**
	 * id to be used by the home app 
	 */
	@Column(name="ETR_EXTERNAL_ID")
	String externalId;
	/**
	 * URI to get the remote entity (http://someDomain:7001/app/external/refs/entityType/<id>
	 * SHOULD return data in JSONP serialization format
	 */
	@Column(name="ETR_REMOTE_URI_CD")
	String remoteURI; 
	/**
	 * the description to be used locally 
	 */
	@Column(name="ETR_DESCRIPTION")
	String description;
	
	/**
	 * use this one to know if the local copy has to be refreshed. 
	 * A service should be available to ask just the last update date from the remote system
	 */
	@Column(name="ETR_REMOTE_MOD_DT")
	@Temporal(value = TemporalType.DATE)
	Date lastKnownRemoteUpdate;
	
	@Column(name="ETR_REMOTE_MOD_VERSION")
	Long lastKnownRemoteUpdateVersion;
	
	public ExternalRef() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExternalRef(Long id, String externalId, String description) {
		super();
		this.id=id;
		this.externalId = externalId;
		this.description = description;
		
		
	}
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.ExternalRefLight#getExternalId()
	 */
	public String getExternalId() {
		return externalId;
	}
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.ExternalRefLight#setExternalId(java.lang.String)
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getRemoteURI() {
		return remoteURI;
	}
	public void setRemoteURI(String remoteURI) {
		this.remoteURI = remoteURI;
	}
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.ExternalRefLight#getDescription()
	 */
	public String getDescription() {
		return description;
	}
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.ExternalRefLight#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastKnownRemoteUpdate() {
		return lastKnownRemoteUpdate;
	}
	public void setLastKnownRemoteUpdate(Date lastKnownRemoteUpdate) {
		this.lastKnownRemoteUpdate = lastKnownRemoteUpdate;
	}
	public Long getLastKnownRemoteUpdateVersion() {
		return lastKnownRemoteUpdateVersion;
	}
	public void setLastKnownRemoteUpdateVersion(Long lastKnownRemoteUpdateVersion) {
		this.lastKnownRemoteUpdateVersion = lastKnownRemoteUpdateVersion;
	}
	
	
	
	
	
}
