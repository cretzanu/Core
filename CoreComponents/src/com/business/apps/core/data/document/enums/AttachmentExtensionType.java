package com.business.apps.core.data.document.enums;

import java.util.Locale;

/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:03
 */

//panteni a better way to handle types
public enum AttachmentExtensionType {
	
	PDF("application/pdf"), 
	XLS("application/vnd.ms-excel"),
        XLSX("application/vnd.ms-excel"),
	DOC("application/msword"),
	DOCX("application/msword"), 
	PPS("application/vnd.ms-powerpoint"),
	PPT("application/vnd.ms-powerpoint"), 
	XML("text/xml"), 
	HTM("text/html"), 
	TXT("text/plain"), 
	BMP("image/bmp"), 
	GIF("image/gif"),
	JPG("image/jpg"), 
	PEG("image/pjpeg"), 
        PNG("image/x-png"), 
	TIF("image/tiff"), 
        DAT("application/msword"),
	MSG("application/msoutlook"), 
	HTML("text/html"), 
	ZIP("application/zip"), 
	RTF("text/richtext"),
        UNKNOWN(""),// deprecated we should stop using it and and updated all database values from UNKNOWN ---> OTHER_DOCUMENT
	OTHER_DOCUMENT("");

	private AttachmentExtensionType(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public static AttachmentExtensionType fromString(String value) {
		try {
			return valueOf(value.toUpperCase(Locale.ENGLISH));
		} catch (RuntimeException ex) {
			return OTHER_DOCUMENT;
		}
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	private String abbreviation;
}