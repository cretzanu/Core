package com.enterprise.core.test.metadata;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.enterprise.core.data.document.MetaData;
import com.enterprise.core.data.document.enums.AttachmentType;
import com.enterprise.core.data.utils.GuiLabelParser;
import com.enterprise.core.data.utils.Language;


public class TestMetaDataModel {
	@Test
	public void testInternationalizationMetaData() {
		MetaData metaData = new MetaData(AttachmentType.PRICE_REQUEST
				.toString(),
				"[EN]PriceRequest;[FR]Demande de Prix;[ES]Petición Precio",
				AttachmentType.class.getSimpleName(), new Date(), new Date());
		
		GuiLabelParser parser = new GuiLabelParser(metaData.getValue());
		System.out.println(parser.getTextValue(Language.ENGLISH));
		System.out.println(parser.getTextValue(Language.FRENCH));
		//System.out.println(parser.getTextValue(Language.SPANISH));
		
		Assert.assertEquals("PriceRequest", parser.getTextValue(Language.ENGLISH));
		Assert.assertEquals("Demande de Prix", parser.getTextValue(Language.FRENCH));
		Assert.assertEquals("Petición Precio", parser.getTextValue(Language.SPANISH));
		
		Assert.assertEquals(parser.getTextValue(Language.ENGLISH), metaData.getValue(Language.ENGLISH));
		Assert.assertEquals(parser.getTextValue(Language.FRENCH), metaData.getValue(Language.FRENCH));
		
		
		
		
		
	}

}
