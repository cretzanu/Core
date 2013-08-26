package com.business.apps.core.data.utils;

import java.io.Serializable;

public class GuiLabelParserItem implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3198880161376377972L;
		private Language lang;
		private String value;
		private String styleClass;
		
		private GuiLabelParser parent;

		@Deprecated
		public GuiLabelParserItem() {
		}
		
		public GuiLabelParserItem(GuiLabelParser parent, Language lang, String value, String styleClass) {
			this.parent = parent;
			this.lang = lang;
			this.value = value;
			this.styleClass = styleClass;
		}

		public GuiLabelParserItem(GuiLabelParser parent, Language lang, String value) {
			this(parent, lang, value, "");
		}
		
		public String getJavaScript() {
			if ("".equals(styleClass))
				return "";
			
			return "defaultLanguageChanged(event);";
		}

		public String getStyleClass() {
			return styleClass;
		}

		public void setStyleClass(String styleClass) {
			this.styleClass = styleClass;
		}

		public String getLang() {
			return lang.getIso2LanguageCode();
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
			parent.setTextValue(value, lang);
		}
	}