package com.business.apps.core.data.document;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.business.apps.core.data.PersistentEntity;
import com.business.apps.core.data.document.enums.AttachmentInternalStatusType;



/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */
@MappedSuperclass
public abstract class DocumentLine extends PersistentEntity {

	private Integer lineNo;
	private String lineComment;
	

	public DocumentLine(){

	}
//	@Override//this one generates issues for aggregation - remove from lists
//	public int compareTo(Object o) {
//		if (!(o instanceof DocumentLine))
//			return super.compareTo(o);
//		if (this.lineNo==null || ((DocumentLine)o).getLineNo()==null)
//			return super.compareTo(o);
//		return this.lineNo.compareTo(((DocumentLine)o).getLineNo());
//	}
	/**
	 * Used to modify the  status of attachments from Temporary_Cache to Saved
	 */
	public void updateAttachmentCacheInfo() {
		
		for(Attachment att:this.getAttachments()){
			
			if (att.getInternalStatus().equals(AttachmentInternalStatusType.TEMPORARY_CACHE))
				{
					att.setInternalStatus(AttachmentInternalStatusType.SAVED);
				}
		}
		
	}

	public Integer getLineNo(){
		return lineNo;
	}
	
	/**
	 * 
	 * @param newVal
	 */
	public void setLineNo(Integer newVal){
		lineNo = newVal;
	}

	public String getLineComment(){
		return lineComment;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLineComment(String newVal){
		lineComment = newVal;
	}

	public abstract Set<Attachment> getAttachments() ;

	public abstract void setAttachments(Set<Attachment> attachments) ;

	
	
}//end DocumentLine