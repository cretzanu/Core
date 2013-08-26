package com.business.apps.core.data.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {
	 
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider prov)
            throws IOException, JsonProcessingException {

        if (value != null){
	        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        String formattedDate = formatter.format(value);
	 
	        gen.writeString(formattedDate);
	        System.out.println("Date formatter called for value "+value+" ~~~~~~");
        }
    }
}
