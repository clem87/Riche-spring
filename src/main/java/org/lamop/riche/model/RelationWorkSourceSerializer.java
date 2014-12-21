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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant de serialiser une relationWorkSource en évitant d'inclure
 * une source ou une workentité incluant elle même la seslation. Permet
 * d'empecher les boucles de sérialilsation
 *
 * @author clril
 */
public class RelationWorkSourceSerializer extends JsonSerializer<RelationWorkSource> {

    @Override
    public void serialize(RelationWorkSource t, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {

        try {
            jsonGenerator.writeStartObject();
            
            jsonGenerator.writeStringField("extract", t.getExtract());
            jsonGenerator.writeStringField("nature", t.getNature());
            
            Source source = t.getSource();
            Source sourceSer = new Source();
            sourceSer.setId(source.getId());
            sourceSer.setArticleTitle(source.getArticleTitle());
            sourceSer.setTitle(source.getTitle());
            sourceSer.setRelationPerson(source.getRelationPerson());
            jsonGenerator.writeObjectField("source", sourceSer);
            
            WorkEntity workSer = (WorkEntity) t.getWorkEntity().clone();
//            workSer.getRelationWorkSource().clear();
           
//        WorkEntity work = t.getWorkEntity();
//        workSer.setId(work.getId());
//        workSer.setTitle(work.getTitle());
        jsonGenerator.writeObjectField("workEntity", workSer);
            jsonGenerator.writeEndObject();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RelationWorkSourceSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
