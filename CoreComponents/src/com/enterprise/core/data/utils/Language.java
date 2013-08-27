package com.enterprise.core.data.utils;


public enum Language {
	ENGLISH("English","EN","Default"),
	FRENCH("Francais","FR","Par d�faut"),
	ROMANIAN("Romana","RO","Default"),
	SPANISH("Spanish", "ES", "Default");
	private String name;
	private String iso2LanguageCode;
	private String defaultLabel;
	public String getDefaultLabel() {
		return defaultLabel;
	}

	Language(String name, String iso2LanguageCode,String defaultLabel) {
		this.name = name;
		this.iso2LanguageCode = iso2LanguageCode;
		this.defaultLabel = defaultLabel;
	}

	public String getName() {
		return name;
	}

	public String getIso2LanguageCode() {
		return iso2LanguageCode;
	}
	
	public static Language getLanguageByIso2LanguageCode(String iso2LanguageCode) {
		for (Language l : Language.values()) {
			if (l.getIso2LanguageCode().equalsIgnoreCase(iso2LanguageCode)) {
				return l;
			}
		}		
		return Language.ENGLISH;
	}
}
