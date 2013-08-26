package com.business.apps.core.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.collection.PersistentCollection;

/**
 * Base class for all entities
 * 
 * @author Cretzanu
 */

@MappedSuperclass
public abstract class PersistentEntity implements Serializable, Comparable<PersistentEntity> {
	
	//fields used in search predicates
	public static final String CREATED_BY_USER_FIELD = "reference";
	public static final String CREATE_DATE_FIELD = "dateCreated";
	
	private static final String STRING = "}";
	private static final String UPDATED_BY_USER = "updatedByUser=";
	private static final String DATE_UPDATED = "dateUpdated=";
	private static final String VERSION = "version=";
	private static final String SEMICOLON = ";";
	private static final String ID = "id=";
	private static final String BRACK = "{";
	
	private static final long serialVersionUID = -4803471783122679780L;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	/**
	 * @Version is used for Optimistic Locking mechanism!!!!
	 */
	@Version
	private Integer version;
	private String createdByUser="system";
	private String updatedByUser="system";
	/**
	 * Gives a chance to the non-Java clients (e.g. JS with JSON) to specify that this object has been modified (e.g. in the GUI).
	 * @see request.js-->buildUiModel()
	 */
	@Transient
    private int dirty=-1;
	

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dateCreated", nullable = false)
	private Date dateCreated = Calendar.getInstance().getTime();

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dateUpdated", nullable = false)
	private Date dateUpdated = Calendar.getInstance().getTime();
	
	/**
	 * The default constructor is always needed for JPA.
	 */
	public PersistentEntity() {
		super();
	}

	public PersistentEntity(Long id) {
		this();
		this.id = id;

	}

	@SuppressWarnings("unchecked")
	/**
	 * Use it to get rid of the Hibernate specific stuff, like lazy initialized collections, when the object has to be delivered to clients 
	 * outside the service container
	 */
	public void purify(){
		//TODO: any general behavior
	}
	
	public <T> Collection<T> purifyCollection(Collection<? extends T> source, Collection<T> purifiedDestination){
		if (PersistentCollection.class.isAssignableFrom(source.getClass())) {
		       if (((PersistentCollection) source).wasInitialized()) {
		    	   purifiedDestination.addAll(source);
		    }
		}
		return purifiedDestination; //ok, not really needed but leave it like this maybe in the future we have to clone something
	}
	
	/**
	 * Provide the right equals implementation. Really mandatory. This
	 * implementation is based on the object's ID. However, if the object has
	 * not been yet serialized to the DB, hence, no ID, then provide the usual
	 * implementation.
	 */
	@Override
	public final boolean equals(Object obj) {
		//System.out.println("equals "+obj);
		if (this==obj)
			return true;
		return (obj instanceof PersistentEntity)  && (this.compareTo((PersistentEntity) obj) == 0);
	}

	/**
	 * Provide the right hashcode implementation. Really mandatory. This
	 * implementation is based on the object's ID. However, if the object has
	 * not been yet serialized to the DB, hence, no ID, then go on with the
	 * usual implementation.
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
		return result;
	}
	
	@Override
	public final int compareTo(PersistentEntity o) {
		 if (!(o instanceof PersistentEntity))
		      throw new ClassCastException("A DigitEntity object expected to be compared against this:"+this.getClass());
		    Long oId = ((PersistentEntity) o).getId();
		    if (this.id==null && oId==null)
		    	if (this==o) return 0;
		    	else {
		    		//System.out.println("-----------compare on hashCodes (this/that): ("+this.hashCode()+ "/"+o.hashCode());
		    		return Integer.valueOf(this.hashCode()).compareTo(Integer.valueOf(o.hashCode()));
		    	}
		    if (this.id==null && oId!=null)
		    	return -1;
		    if (this.id.equals(oId))
		    	return 0;
		    if (this.id<oId)
		    	return -1;
		    else
		    	return 1;
		    
	}


	public static void testFundamentals(){
		class TestFundamentals extends PersistentEntity{};
		
		PersistentEntity e1=new TestFundamentals();
		PersistentEntity e2=new TestFundamentals();
		testFundamentals(e1,e2);
		e1.setId(1L);
		e2.setId(2L);
		testFundamentals(e1,e2);
		
		
		
	}
	public static void testFundamentals(PersistentEntity e1,	PersistentEntity e2){
		if (e1.equals(null))
			throw new RuntimeException ("VERY VERY BAD -0!!!!");
		if (e1.equals(e2) && e1.getId()!=e2.getId() )
			throw new RuntimeException ("VERY VERY BAD -1!!!!");
		if (e1.equals(e2) && e1.getId()==null && e2.getId()==null && (e1!=e2))
			throw new RuntimeException ("VERY VERY BAD -2!!!!");
		TreeSet set = new TreeSet();
		set.add(e1);
		set.add(e2);
		if (set.size()!=2)
			throw new RuntimeException ("VERY VERY BAD -3!!!!");
		
		if (!set.contains(e1))
			throw new RuntimeException ("VERY VERY BAD -4!!!!");
		set.remove(e1);
		if (set.contains(e1))
			throw new RuntimeException ("VERY VERY BAD -5!!!!");
		set.remove(e2);
		
		if (set.size()!=0)
			throw new RuntimeException ("VERY VERY BAD -6!!!!");
		
		List lst = new LinkedList();
		lst.add(e1);
		lst.add(e2);
		if (lst.size()!=2)
			throw new RuntimeException ("VERY VERY BAD -7!!!!");
		
		if (!lst.contains(e1))
			throw new RuntimeException ("VERY VERY BAD -8!!!!");
		
	}
	@Override
	public String toString() {

		return new StringBuilder().append(super.toString()).append(BRACK).append(ID)
				.append(id).append(SEMICOLON).append(VERSION).append(version)
				.append(SEMICOLON).append(DATE_UPDATED).append(dateUpdated).append(
						SEMICOLON).append(UPDATED_BY_USER).append(updatedByUser)
				.append(STRING).toString();
	}

	// ---------------public GETTERS and SETTERS - the usual Java Beans
	// approach-----------------------//
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#getCreatedByUser()
	 */
	public String getCreatedByUser() {
		return createdByUser;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#getDateCreated()
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#getDateUpdated()
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}

	
	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#getUpdatedByUser()
	 */
	public String getUpdatedByUser() {
		return updatedByUser;
	}

	public Integer getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#setCreatedByUser(java.lang.String)
	 */
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#setDateCreated(java.util.Date)
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#setDateUpdated(java.util.Date)
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see eu.cec.digit.apps.meta.data.DigitEntityItf#setUpdatedByUser(java.lang.String)
	 */
	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public int getDirty() {
		return dirty;
	}

	public void setDirty(int dirty) {
		this.dirty = dirty;
	}
	/**
	 * GUI support
	 * @return
	 */
	public boolean dirtyObject(){
		return this.dirty!=-1;
	}

}
