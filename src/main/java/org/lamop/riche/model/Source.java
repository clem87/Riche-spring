/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author clril
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id", scope = Source.class)
public class Source implements Serializable {

    
    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RelationWorkSource> relationWorkSource = new ArrayList<>();

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected String title;

    protected String articleTitle;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Person> authors = new ArrayList<>();

    @ManyToOne
    protected BibliographicType bibliographicType;

    protected String releaseTown;

    protected Integer releaseYear;

    protected Integer volume;

    protected String series;

    protected String publisher;

    protected String editor;

    protected Integer num;

    public Long getId() {
        return id;
    }

//    @JsonIgnore
//    @ManyToMany(mappedBy = "sources")
//    private List<WorkEntity> works;
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Source)) {
            return false;
        }
        Source other = (Source) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.Source[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

//    public List<BibliograficAuthor> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(List<BibliograficAuthor> authors) {
//        this.authors = authors;
//    }

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }
    
    

    public BibliographicType getBibliographicType() {
        return bibliographicType;
    }

    public void setBibliographicType(BibliographicType bibliographicType) {
        this.bibliographicType = bibliographicType;
    }

    public String getReleaseTown() {
        return releaseTown;
    }

    public void setReleaseTown(String releaseTown) {
        this.releaseTown = releaseTown;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

//    public List<WorkEntity> getWorks() {
//        return works;
//    }
//
//    public void setWorks(List<WorkEntity> works) {
//        this.works = works;
//    }
    public List<RelationWorkSource> getRelationWorkSource() {
        return relationWorkSource;
    }

    public void setRelationWorkSource(List<RelationWorkSource> relationWorkSource) {
        this.relationWorkSource = relationWorkSource;
    }

    public synchronized void addRelationWorkSource(RelationWorkSource r) {

        this.relationWorkSource.add(r);

    }

    public synchronized void removeRelationWorkSource(RelationWorkSource r) {
        this.relationWorkSource.remove(r);
    }

}
