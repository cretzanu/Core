package com.enterprise.core.data.document;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.enterprise.core.data.PersistentEntity;


/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//has to be fast....this is why
@DiscriminatorColumn(      name="ITM_TYPE_CD", discriminatorType=DiscriminatorType.STRING )
@Table(name="REQ_TB_ITEM")

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")//http://www.cowtowncoder.com/blog/archives/2010/03/entry_372.html


@AttributeOverrides( {
	 @AttributeOverride(name = "id", column = @Column(name = "ITM_ID"))
	 ,@AttributeOverride(name = "version", column = @Column(name = "ITM_VERSION_NO"))
	 ,@AttributeOverride(name = "createdByUser", column = @Column(name = "ITM_CRE_ID"))
	 ,@AttributeOverride(name = "updatedByUser", column = @Column(name = "ITM_MOD_ID"))
	 ,@AttributeOverride(name = "dateCreated", column = @Column(name = "ITM_CRE_DT"))
	 ,@AttributeOverride(name = "dateUpdated", column = @Column(name = "ITM_MOD_DT"))
	 ,@AttributeOverride(name = "entity_type", column = @Column(name = "ITM_ENTITY_TYPE"))
	 
    } )
public class Item extends PersistentEntity {
    @Column(name="ITM_LABEL_DESC")
	private String label;
    @Column(name="ITM_ITEM_DESC")
	private String description;

	public Item(){

	}

	
	public void finalize() throws Throwable {
		super.finalize();
	}
	public String getLabel(){
		return label;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLabel(String newVal){
		label = newVal;
	}

	public String getDescription(){
		return description;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDescription(String newVal){
		description = newVal;
	}
}//end Item