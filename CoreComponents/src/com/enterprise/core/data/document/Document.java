package com.enterprise.core.data.document;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.enterprise.core.data.PersistentEntity;
import com.enterprise.core.data.document.enums.AttachmentInternalStatusType;


/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */

@MappedSuperclass
public abstract class Document extends PersistentEntity {

	private String reference;
	private String internalReference;
	private String subject;
	private Integer year;
	private Integer month;
	private Integer sequence;
	private String comment;
	
	@ManyToOne
	private ContactPersonDetail contactPersonDetail;
	@Embedded
	private Context context;

	

	
	public Document(){

	}
	/**
	 * Used to modify the  status of attachments from Temporary_Cache to Saved.
	 * Please @see the corresponding REST service (e.g. DemandController)
	 */
	public void updateAttachmentCacheInfo() {
		
		for(Attachment att:this.getAttachments()){
			
			if (att.getInternalStatus().equals(AttachmentInternalStatusType.TEMPORARY_CACHE))
				{
				//System.out.println("modifying stauts for new attachment:"+att.getId());
				att.setInternalStatus(AttachmentInternalStatusType.SAVED);
				}
		}
		
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

	public String getInternalReference(){
		return internalReference;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setInternalReference(String newVal){
		internalReference = newVal;
	}

	public String getSubject(){
		return subject;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSubject(String newVal){
		subject = newVal;
	}

	public Integer getYear(){
		return year;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setYear(Integer newVal){
		year = newVal;
	}

	public Integer getMonth(){
		return month;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMonth(Integer newVal){
		month = newVal;
	}

	public Integer getSequence(){
		return sequence;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSequence(Integer newVal){
		sequence = newVal;
	}

	public String getComment(){
		return comment;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setComment(String newVal){
		comment = newVal;
	}

	
	public ContactPersonDetail getContactPersonDetail(){
		return contactPersonDetail;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setContactPersonDetail(ContactPersonDetail newVal){
		contactPersonDetail = newVal;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public abstract Set<Attachment> getAttachments();

	/**
	 * 
	 * @param newVal
	 */
	public abstract void setAttachments(Set<Attachment> newVal);	

	
}//end Document