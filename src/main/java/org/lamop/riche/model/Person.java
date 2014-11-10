/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author clril
 */
@Entity
public class Person implements Serializable {

    @JsonCreator
    public Person() {
    }
    
    
    
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private List<WorkEntity> works;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonIgnore
    @ManyToMany
    protected List<AuthorityNotice> notices; 
    


    @JsonIgnore
    @OneToMany(mappedBy = "person")
    protected List<SecondaryName> secondarynames;
  
    
    protected String label;

    public List<WorkEntity> getWorks() {
        return works;
    }

    public void setWorks(List<WorkEntity> works) {
        this.works = works;
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AuthorityNotice> getNotices() {
        return notices;
    }

    public void setNotices(List<AuthorityNotice> notices) {
        this.notices = notices;
    }

    public List<SecondaryName> getSecondarynames() {
        return secondarynames;
    }

    public void setSecondarynames(List<SecondaryName> secondarynames) {
        this.secondarynames = secondarynames;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.Person[ id=" + id + " ]";
    }
    
}
