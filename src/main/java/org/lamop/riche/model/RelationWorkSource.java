/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author clril
 */
@Entity
@JsonSerialize(using = RelationWorkSourceSerializer.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
//        property = "@id", scope = RelationWorkSource.class)
public class RelationWorkSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToOne(targetEntity = WorkEntity.class)
//    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    WorkEntity workEntity;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToOne(targetEntity = Source.class)
//    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    Source source;

    String extract;
    
    String tome;
    
    
    

    /**
     * *
     * Indique di c'est de la biblio une traduction etc
     */
    String nature;

    String note;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.workEntity);
        hash = 29 * hash + Objects.hashCode(this.source);
        hash = 29 * hash + Objects.hashCode(this.extract);
        hash = 29 * hash + Objects.hashCode(this.tome);
        hash = 29 * hash + Objects.hashCode(this.nature);
        hash = 29 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RelationWorkSource other = (RelationWorkSource) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.workEntity, other.workEntity)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.extract, other.extract)) {
            return false;
        }
        if (!Objects.equals(this.tome, other.tome)) {
            return false;
        }
        if (!Objects.equals(this.nature, other.nature)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "org.lamop.riche.model.RWorkSource[ id=" + id + " ]";
    }

    public WorkEntity getWorkEntity() {
        return workEntity;
    }

    public void setWorkEntity(WorkEntity workEntity) {
        this.workEntity = workEntity;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getTome() {
        return tome;
    }

    public void setTome(String tome) {
        this.tome = tome;
    }
    
    
    
    
}
