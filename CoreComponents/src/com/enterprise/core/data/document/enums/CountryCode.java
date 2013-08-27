package com.enterprise.core.data.document.enums;


/**
 * 	@author batrian
 *	@version 1.00
 * 	@created 29-Nov-2012 14:19:47
 */
public enum CountryCode {
	AU, //Austria (1995)
	BE, //Belgium (1952)
	BG, //Bulgaria (2007)
	CY, //Cyprus (2004)
	CZ, //Czech Republic (2004)
	DK, //Denmark (1973)
	EE, //Estonia (2004)
	FI, //Finland (1995)
	FR, //France (1952)
	DE, //Germany (1952)
	GR, //Greece (1981)
	HU, //Hungary (2004)
	IE, //Ireland (1973)
	IT, //Italy (1952)
	LV, //Latvia (2004)
	LT, //Lithuania (2004)
	LU, //Luxembourg (1952)
	MT, //Malta (2004)
	NL, //Netherlands (1952)
	PL, //Poland (2004)
	PT, //Portugal (1986)
	RO, //Romania (2007)
	SK, //Slovakia (2004)
	SI, //Slovenia (2004)
	ES, //Spain (1986)
	SE, //Sweden (1995)
	UK, //United Kingdom (1973)
	HR, //Croatia
	CH, //Switzerland
	US, //United States of America
	OT; //Others
	
	   static public boolean isMember(String code) {
	       for (CountryCode countryCode : CountryCode.values()){
	           if (countryCode.name().equals(code)){
	               return true;
	           }
	       }
	       return false;
	   }
	   
	   public String getCode(){
		   return this.name();
	   }
}
