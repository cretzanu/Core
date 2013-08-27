package com.enterprise.core.data.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.util.ISO8601Utils;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
    @Override
    public Date deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = jsonparser.getText(); System.err.println("deserializer resulted in string date: " + date);
        
        Date d;
        try {//parse from the user format
        	d = format.parse(date); 
        } catch (ParseException e) {
        	try {//parse from ISO-8601 format (jackson standard)
        		d = ISO8601Utils.parse(date);
			} catch (IllegalArgumentException e2) {
				throw new RuntimeException(e);
			}
        }
        
        return d;
    }
}
