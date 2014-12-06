/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author clril
 */
@Entity
//@JsonSerialize(using = WorkEntitySerializer.class)
public class WorkEntity implements Serializable {
    
    
  
    @OneToMany()
    @Cascade(CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference("workrelation")
    private List<RelationWorkSource> relationWorkSource = new ArrayList<>();

    @JsonCreator
    public WorkEntity() {
    }
    
    
    
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @JsonSerialize
    protected String title;

    @ManyToMany(fetch = FetchType.EAGER)
    protected List<Person> authors = new ArrayList<>();

    @ManyToOne
    protected Theme theme;

    @OneToOne
    protected Origin origin;

//    @ManyToMany
//    protected List<Source> sources= new ArrayList<>();
    
    
    protected Date exactDate;
    
    
    protected Integer centuryMax;
    
    protected Integer centuryMin;
    

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

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

//    public List<Source> getSources() {
//        return sources;
//    }
//
//    public void setSources(List<Source> sources) {
//        this.sources = sources;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExactDate() {
        return exactDate;
    }

    public void setExactDate(Date exactDate) {
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

    public List<RelationWorkSource> getRelationWorkSource() {
        return relationWorkSource;
    }

    public void setRelationWorkSource(List<RelationWorkSource> relationWorkSource) {
        this.relationWorkSource = relationWorkSource;
    }

    
    public synchronized void removeRelationWorkSource(RelationWorkSource relation){
        this.relationWorkSource.remove(relation);
        
    }
    public synchronized void addRelationWorkSource(RelationWorkSource relation){
        this.relationWorkSource.add(relation);
    }
    
}
