/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author clril
 */
public class SourceDTO implements Serializable, Cloneable {

    private Set<RelationWorkSourceDTO> relationWorkSource = new HashSet<>();

    private Long id;

    protected String title;

    protected String articleTitle;
    
    /***
     * Champs issu de la premiere base de données. Contient une url http vers sudoc persee etc...
     */
    protected String url;

    protected Set<RelationSourcePerson> relationPerson = new HashSet<>();

    protected BibliographicType bibliographicType;

    protected String releaseTown;

    protected Integer releaseYear;

    protected Integer volume;

    protected String series;

    protected String publisher;

    protected String editor;

    protected Integer num;
    
    protected String journal;
    
    

    public Long getId() {
        return id;
    }

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
        if (!(object instanceof SourceDTO)) {
            return false;
        }
        SourceDTO other = (SourceDTO) object;
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

    public Set<RelationWorkSourceDTO> getRelationWorkSource() {
        return relationWorkSource;
    }

    public void setRelationWorkSource(Set<RelationWorkSourceDTO> relationWorkSource) {
        this.relationWorkSource = relationWorkSource;
    }


    public synchronized void addRelationWorkSource(RelationWorkSourceDTO r) {

        this.relationWorkSource.add(r);

    }

    public synchronized void removeRelationWorkSource(RelationWorkSource r) {
        this.relationWorkSource.remove(r);
    }

    public Set<RelationSourcePerson> getRelationPerson() {
        return relationPerson;
    }

    public void setRelationPerson(Set<RelationSourcePerson> relationPerson) {
        this.relationPerson = relationPerson;
    }
    
    
    
    public void addRelationPerson(RelationSourcePerson relation){
        this.relationPerson.add(relation);
    }
    
    public void removeRelationPerson(RelationSourcePerson relation){
        this.relationPerson.remove(relation);
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
