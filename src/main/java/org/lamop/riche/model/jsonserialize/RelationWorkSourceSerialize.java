/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model.jsonserialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.WorkEntity;

/**
 *
 * @author clril
 */
public class RelationWorkSourceSerialize extends JsonSerializer<List<RelationWorkSource>>{

    @Override
    public void serialize(List<RelationWorkSource> list, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {

        jg.writeStartArray();
        
        List<RelationWorkSource> aSerialiser = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RelationWorkSource get = list.get(i);
            RelationWorkSource newtruc = new RelationWorkSource();
            newtruc.setSource(get.getSource());
            newtruc.setNote(get.getNote());
            newtruc.setExtract(get.getExtract());
            aSerialiser.add(newtruc);
        }
        
        for (int i = 0; i < aSerialiser.size(); i++) {
            RelationWorkSource get = aSerialiser.get(i);
            jg.writeObject(get);
        }
        
//         for (int i = 0; i < list.size(); i++) {
//            RelationWorkSource get = list.get(i);
//            
////            jg.writeObject(get.getSource());
////            
//        }
         jg.writeEndArray();
//        jg.write
        
//        jg.writeObjectField("source", t.getSource());
//        jg.writeNumberField("id", t.getId());
//        jg.writeEndObject();
        
    }

  
    
}
