package com.enterprise.core.data.document;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.enterprise.core.data.PersistentEntity;
import com.enterprise.core.data.utils.GuiLabelParser;
import com.enterprise.core.data.utils.Language;


/**
 * Key-value pairs, especially needed for enums internationalization.
 * The value is internationalized in-place having the format: [EN]englishTxt;[FR]frenchTxt;[ES]spanishTxt.
 * 
 * @author Liviu Gabriel Cretu
 *
 */
@Entity
@Table(name="REQ_TB_METADATA")

 @AttributeOverrides( {
	 @AttributeOverride(name = "id", column = @Column(name = "MTD_ID"))
	 ,@AttributeOverride(name = "version", column = @Column(name = "MTD_VERSION_NO"))
	 ,@AttributeOverride(name = "createdByUser", column = @Column(name = "MTD_CRE_ID"))
	 ,@AttributeOverride(name = "updatedByUser", column = @Column(name = "MTD_MOD_ID"))
	 ,@AttributeOverride(name = "dateCreated", column = @Column(name = "MTD_CRE_DT"))
	 ,@AttributeOverride(name = "dateUpdated", column = @Column(name = "MTD_MOD_DT"))
	 ,@AttributeOverride(name = "entity_type", column = @Column(name = "MTD_ENTITY_TYPE"))
	 
    } )
public class MetaData extends PersistentEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6198485206267159622L;
	public static final String TYPE 		= "type";
	public static final String KEY			= "key";
	public static final String VALUE		= "value";
	public static final String VALID_FROM	= "validFrom";
	public static final String VALID_TO		= "validTo";

	@Column(name="MTD_KEY_CD")
	private String key;
	@Column(name="MTD_VALUE_MT")
	private String value;  // format: [EN]englishTxt;[FR]frenchTxt;[ES]spanishTxt.
	@Column(name="MTD_TYPE_CD")
	private String type;
	@Temporal(value = TemporalType.DATE)
	@Column(name="MTD_FROM_DT")
	private Date validFrom;
	@Temporal(value = TemporalType.DATE)
	@Column(name="MTD_TO_DT")
	private Date validTo;
	
	public MetaData(){}
	
	public MetaData(String key, String value, String type, Date validFrom,
			Date validTo) {
		super();
		this.key = key;
		this.value = value;
		this.type = type;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}
	/**
	 * applies @see GuiLabelParser 
	 *  
	 */
	public String getValue(Language lang){
		return new GuiLabelParser(this.getValue()).getTextValue(lang);
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
	
}
