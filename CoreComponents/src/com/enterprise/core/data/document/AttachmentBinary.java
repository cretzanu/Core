package com.enterprise.core.data.document;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.enterprise.core.data.PersistentEntity;

@Entity
@Table(name="REQ_TB_ATTACHMENTS_BINARY")
@AttributeOverrides( {
	 @AttributeOverride(name = "id", column = @Column(name = "ABI_ID"))
	 ,@AttributeOverride(name = "version", column = @Column(name = "ABI_VERSION_NO"))
	 ,@AttributeOverride(name = "createdByUser", column = @Column(name = "ABI_CRE_ID"))
	 ,@AttributeOverride(name = "updatedByUser", column = @Column(name = "ABI_MOD_ID"))
	 ,@AttributeOverride(name = "dateCreated", column = @Column(name = "ABI_CRE_DT"))
	 ,@AttributeOverride(name = "dateUpdated", column = @Column(name = "ABI_MOD_DT"))
	 ,@AttributeOverride(name = "entity_type", column = @Column(name = "ABI_ENTITY_TYPE"))
	 
   } )
public class AttachmentBinary extends PersistentEntity{
	private static final long	serialVersionUID	= 2L;
	public static final String SQL_SELECT_FOR_ATTACHMENT_STREAMING = "select ABI_FILE from REQ_TB_ATTACHMENTS_BINARY where ABI_ID= (Select ATT_BINARY_ID from REQ_TB_ATTACHMENT where ATT_ID=";
	@Lob 
	@Column(name="ABI_FILE", updatable=false)
	private byte[] file;

	@JsonIgnore
	public byte[] getFile() {
		return file;
	}

	@JsonIgnore
	public void setFile(byte[] file) {
		this.file = file;
	}
}
