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
 * Classe permettant de serialiser une relationWorkSource en évitant d'inclure
 * une source ou une workentité incluant elle même la seslation. Permet
 * d'empecher les boucles de sérialilsation
 *
 * @author clril
 */
public class RelationWorkSourceSerializer extends JsonSerializer<RelationWorkSource> {

    @Override
    public void serialize(RelationWorkSource t, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {

            jsonGenerator.writeStartObject();
            
            jsonGenerator.writeStringField("extract", t.getExtract());
            jsonGenerator.writeStringField("nature", t.getNature());
            jsonGenerator.writeNumberField("id", t.getId());
            
            Source source = t.getSource();
            Source sourceSer = new Source();
            sourceSer.setId(source.getId());
            sourceSer.setArticleTitle(source.getArticleTitle());
            sourceSer.setTitle(source.getTitle());
            jsonGenerator.writeObjectField("source", sourceSer);
            
            WorkEntity workSer = new WorkEntity();
            workSer.setId(t.getWorkEntity().getId());
            workSer.setTitle(t.getWorkEntity().getTitle());

        jsonGenerator.writeObjectField("workEntity", workSer);
            jsonGenerator.writeEndObject();

    }

}
