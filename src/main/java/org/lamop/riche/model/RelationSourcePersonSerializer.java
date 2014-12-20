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
public class RelationSourcePersonSerializer extends JsonSerializer<RelationSourcePerson>{

    @Override
    public void serialize(RelationSourcePerson relation, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {
        
     
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("rolePublication", relation.getRolePublication());
        
        Source sourceSer = new Source();
        sourceSer.setId(relation.getSource().getId());
        sourceSer.setTitle(relation.getSource().getTitle());
//        relation.getSource().getRelationPerson().clear();
//        relation.getSource().getRelationWorkSource().clear();
        jsonGenerator.writeObjectField("source", sourceSer);
//        
//        relation.getPerson().getRelationSource().clear();
        
        Person p = new Person();
        p.setId(relation.getPerson().getId());
        p.setLabel(relation.getPerson().getLabel());
        jsonGenerator.writeObjectField("person", p);
        
        jsonGenerator.writeEndObject();
    
        
//        jsonGenerator.writeStringField("username", user.getUsername());
//        jsonGenerator.writeEndObject();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





    
}
