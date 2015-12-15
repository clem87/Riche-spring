/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author clril
 */

public class WorkEntityDTO implements Serializable {

    @JsonCreator
    public WorkEntityDTO() {
    }

    private Set<RelationWorkSourceDTO> relationWorkSource = new HashSet<>();

    private static final long serialVersionUID = 1L;

    private Long id;

    protected String title;

    protected Set<WorkAuthor> authors = new HashSet<>();

    protected Set<Theme> theme;

    protected String origin;

    protected String exactDate;

    protected Integer centuryMax;

    protected Integer centuryMin;

    /**
     * *
     * Note personnelle de Pierre Riché sur l'oeuvre
     */
    protected String note;

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Set<WorkAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<WorkAuthor> authors) {
        this.authors = authors;
    }

    public Set<Theme> getTheme() {
        return theme;
    }

    public void setTheme(Set<Theme> theme) {
        this.theme = theme;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExactDate() {
        return exactDate;
    }

    public void setExactDate(String exactDate) {
        this.exactDate = exactDate;
    }

    public Integer getCenturyMax() {
        return centuryMax;
    }

    public void setCenturyMax(Integer centuryMax) {
        this.centuryMax = centuryMax;
    }

    public Integer getCenturyMin() {
        return centuryMin;
    }

    public void setCenturyMin(Integer centuryMin) {
        this.centuryMin = centuryMin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkEntityDTO)) {
            return false;
        }
        WorkEntityDTO other = (WorkEntityDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.Work[ id=" + id + " ]";
    }



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<RelationWorkSourceDTO> getRelationWorkSource() {
        return relationWorkSource;
    }

    public void setRelationWorkSource(Set<RelationWorkSourceDTO> relationWorkSource) {
        this.relationWorkSource = relationWorkSource;
    }

    /***
     * Charge la DTO avec les données d'une entite
     * @param entity 
     */
    public void feedWithEntity(WorkEntity entity){
        
        this.title = entity.getTitle();
        this.id = entity.getId();
        this.centuryMax = entity.getCenturyMax();
        this.centuryMin = entity.getCenturyMin();
    }


}
