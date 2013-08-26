package com.business.apps.core.data.utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * takes a string in form  "[lg1]lgLabel1;[lg2]lgLabel2;[lg3]lgLabel3" and parses it
 * */
public class GuiLabelParser implements Serializable{

	
	private static final long serialVersionUID = -4691215128479452651L;
		private String textValue = "";
		private Language language = Language.FRENCH;
		
		@Deprecated
		public GuiLabelParser() {
		}
		
		public GuiLabelParser(String defaultValue) {
			setTextValue(defaultValue);
		}

		public GuiLabelParser(Language language) {
			this.language = language;
		}
		
		public String getTextValue() {
			return textValue;
		}

		public void setTextValue(String textValue) {
			if (textValue == null) {
				textValue = "";
			}
			
			if (! textValue.contains("[")) {
				GuiLabelParser guiLabel = new GuiLabelParser();
				guiLabel.setTextValue(textValue, Language.ENGLISH);
				this.textValue = guiLabel.getTextValue();
				return;
			}

			this.textValue = textValue;
		}
		
		final static Pattern pattern = Pattern.compile("\\[(.*?)\\](.*)");
		
		private Map<String, String> mapFromText() {
			Map<String, String> result = new HashMap<String, String>();
			
			String[] expressions = textValue.split(";");
			
			for (int i = 0; i < expressions.length; i++) {
				Matcher m = pattern.matcher(expressions[i]);
				if (m.matches()) {
					result.put(m.group(1), m.group(2));
				} else {
					if (!(i == expressions.length - 1 && "".equals(expressions[i]))) {
						//asserting(false,"bug: empty value for text");					
					}
				}
			}
			
			return result;
		}
		
		private void mapToText(Map<String, String> map) {
			textValue = "";
			for (String key : map.keySet()) {
				textValue += "[" + key + "]" + map.get(key) + ";";
			}
		}

		public String getTextValue(String language) {
			String result;
			
			result = mapFromText().get(language);
			
			if (result == null)
				return "";
			
			return result;
		}
		
		public String getTextValue(Language l) {
			return getTextValue(l.getIso2LanguageCode());
		}
		
		public void setTextValue(String textValue, Language l) {
			Map<String, String> mapping = mapFromText();
			mapping.put(l.getIso2LanguageCode(), textValue);
			mapToText(mapping);
		}
		
		public void setTextValue(String value, String language) {
			this.setTextValue(value, Language.getLanguageByIso2LanguageCode(language));
		}
		
		List<GuiLabelParserItem> result ;
		public List<GuiLabelParserItem> getItems() {
			if(result != null){
					return result;
			}
			 result = new ArrayList<GuiLabelParserItem>();
			
			for (Language l : Language.values()) {
					result.add( new GuiLabelParserItem( this, l, getTextValue(l) ) );				
			}
			
			return result;
		}
		
		public String getStringItems() {
			boolean first = true;
			String result = "";
			for (Language l : Language.values()) {
				if (first) {
					first = false;
					result = l.getIso2LanguageCode();
				} else {
					result += "," + l.getIso2LanguageCode();
				}
			}
			
			return result;
		}
		
		/**
		 * Functionally equivalent to:
		 * <code>new GuiLabelParser(value).toString()</code>
		 * @param value
		 * @return
		 */
		public static String parse(String value) {
			return new GuiLabelParser(value).toString();
		}
		
		public String parseByLanguage(String value) {
			setTextValue(value);
			return getTextValue(this.language);
		}
		
		public String append(String value, boolean atTheEnd) {
			for( GuiLabelParserItem item : this.getItems()){
				if(atTheEnd)
					this.setTextValue(item.getValue().concat(value), item.getLang());
				else
					this.setTextValue(value.concat(item.getValue()), item.getLang());
			}
			return this.getTextValue();
		}
}