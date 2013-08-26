package com.business.apps.core.data.document;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.business.apps.core.data.PersistentEntity;
import com.business.apps.core.data.document.enums.AttachmentExtensionType;
import com.business.apps.core.data.document.enums.AttachmentInternalStatusType;
import com.business.apps.core.data.document.enums.AttachmentType;
import com.business.apps.core.data.document.enums.DocumentStateType;
import com.business.apps.core.data.document.enums.MIMEType;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */
@Entity
@Table(name = "REQ_TB_ATTACHMENT")
@AttributeOverrides( {
	 @AttributeOverride(name = "id", column = @Column(name = "ATT_ID"))
	 ,@AttributeOverride(name = "version", column = @Column(name = "ATT_VERSION_NO"))
	 ,@AttributeOverride(name = "createdByUser", column = @Column(name = "ATT_CRE_ID"))
	 ,@AttributeOverride(name = "updatedByUser", column = @Column(name = "ATT_MOD_ID"))
	 ,@AttributeOverride(name = "dateCreated", column = @Column(name = "ATT_CRE_DT"))
	 ,@AttributeOverride(name = "dateUpdated", column = @Column(name = "ATT_MOD_DT"))
	 ,@AttributeOverride(name = "entity_type", column = @Column(name = "ATT_ENTITY_TYPE"))
	 
   } )

@NamedQueries ({
	  @NamedQuery (name=Attachment.GET_ATT_BY_REF_CD, query="Select a from Attachment a where a.reference=:reference AND a.internalStatus='TEMPORARY_CACHE' order by a.id desc")
})
public class Attachment extends PersistentEntity {
	private static final long	serialVersionUID	= 3L;

	public static final String GET_ATT_BY_ID = "Select a from Attachment a where a.id=:id";
	public static final String GET_ATT_BY_REF_CD = "Attachment.RETRIEVE_ALL_ATT_BY_REF";
	
	@Column(name="ATT_REF_CD")
	private String reference;
	@Column(name="ATT_TYPE_CD")
	@Enumerated(EnumType.STRING)
	private AttachmentType type=AttachmentType.OTHER_DOCUMENT;
	@Column(name="ATT_STATE_CD")
	@Enumerated(EnumType.STRING)
	private DocumentStateType state=DocumentStateType.DRAFT;
	@Column(name="ATT_EXTENSION_CD")
	@Enumerated(EnumType.STRING)
	private AttachmentExtensionType extension=AttachmentExtensionType.UNKNOWN;
	@Column(name="ATT_MIMETYPE_NO")
	@Enumerated(EnumType.STRING)
	private MIMEType mimeType=MIMEType.JSON;//TODO to be removed
	/**
	 * Field and specific default value used for lifeCycleManagement @See DemandController.  
	 * DO NOT modify this default value unless you modify the REST services behaviour too 
	 */
	@Column(name="ATT_INTERNAL_STATUS_FL")
	@Enumerated(EnumType.STRING)
	private AttachmentInternalStatusType internalStatus=AttachmentInternalStatusType.TEMPORARY_CACHE; 
	@Column(name="ATT_FILE_NM")
	private String fileName;
	@Column(name="ATT_FILESIZE_NO")
	private Integer fileSize;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ATT_BINARY_ID", updatable=false)
	//@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	
        @JsonIgnore
	private AttachmentBinary binary;
       
        @Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ATT_UPLOAD_DT", nullable = false)
        private Date dateUploaded = Calendar.getInstance().getTime(); 

	public Attachment(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public String getReference(){
		return reference;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setReference(String newVal){
		reference = newVal;
	}

	public AttachmentType getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(AttachmentType newVal){
		type = newVal;
	}

	public DocumentStateType getState(){
		return state;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setState(DocumentStateType newVal){
		state = newVal;
	}

	public AttachmentExtensionType getExtension(){
		return extension;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setExtension(AttachmentExtensionType newVal){
		extension = newVal;
	}

	public MIMEType getMimeType(){
		return mimeType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMimeType(MIMEType newVal){
		mimeType = newVal;
	}

	

	public String getFileName(){
		return fileName;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFileName(String newVal){
		fileName = newVal;
	}


	public Integer getFileSize(){
		return fileSize;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFileSize(Integer newVal){
		fileSize = newVal;
	}

	

	public AttachmentInternalStatusType getInternalStatus() {
		return internalStatus;
	}

	public void setInternalStatus(AttachmentInternalStatusType internalStatus) {
		this.internalStatus = internalStatus;
	}

	public AttachmentBinary getBinary() {
		return binary;
	}

	
	public void setBinary(AttachmentBinary binary) {
		this.binary = binary;
	}

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

	

	
	
}//end Attachment