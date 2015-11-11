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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author clril
 */
@Entity
public class WorkEntity implements Serializable, Cloneable {

    @JsonCreator
    public WorkEntity() {
    }

//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "workEntity", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    private Set<RelationWorkSource> relationWorkSource = new HashSet<>();

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected String title;

    @ManyToMany()
    protected Set<WorkAuthor> authors = new HashSet<>();

    @ManyToMany
    protected Set<Theme> theme;

    protected String origin;

    protected String exactDate;

    protected Integer centuryMax;

    protected Integer centuryMin;

    /**
     * *
     * Note personnelle de Pierre Riché sur l'oeuvre
     */
    @Column(length = 700)
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
        if (!(object instanceof WorkEntity)) {
            return false;
        }
        WorkEntity other = (WorkEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.Work[ id=" + id + " ]";
    }

    public Set<RelationWorkSource> getRelationWorkSource() {
        return relationWorkSource;
    }

    public void setRelationWorkSource(Set<RelationWorkSource> relationWorkSource) {
        this.relationWorkSource = relationWorkSource;
    }

    public synchronized void removeRelationWorkSource(RelationWorkSource relation) {

        for (Iterator<RelationWorkSource> iterator = relationWorkSource.iterator(); iterator.hasNext();) {
            RelationWorkSource next = iterator.next();
            if (next.getId().equals(relation.getId())) {
                iterator.remove();
            }
        }
    }

    public synchronized void addRelationWorkSource(RelationWorkSource relation) {
        this.relationWorkSource.add(relation);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        WorkEntity clone = (WorkEntity) super.clone();
        clone.setRelationWorkSource(null);

        return clone; //To change body of generated methods, choose Tools | Templates.
    }

}
