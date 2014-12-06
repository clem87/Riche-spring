/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 *
 * @author clril
 */
public class WorkEntitySerializer extends JsonSerializer<WorkEntity>{

    @Override
    public void serialize(WorkEntity t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        
        jg.writeStartObject();
        jg.writeStringField("title", t.getTitle());
        jg.writeEndObject();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
