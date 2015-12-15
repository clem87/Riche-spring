/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.ManyToOne;

/**
 *
 * @author clril
 */
public class RelationWorkSourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    WorkEntityDTO workEntity;

    @ManyToOne
    SourceDTO source;

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
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.workEntity);
        hash = 53 * hash + Objects.hashCode(this.source);
        hash = 53 * hash + Objects.hashCode(this.extract);
        hash = 53 * hash + Objects.hashCode(this.tome);
        hash = 53 * hash + Objects.hashCode(this.nature);
        hash = 53 * hash + Objects.hashCode(this.note);
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
        final RelationWorkSourceDTO other = (RelationWorkSourceDTO) obj;
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

    public WorkEntityDTO getWorkEntity() {
        return workEntity;
    }

    public void setWorkEntity(WorkEntityDTO workEntity) {
        this.workEntity = workEntity;
    }

    public SourceDTO getSource() {
        return source;
    }

    public void setSource(SourceDTO source) {
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
