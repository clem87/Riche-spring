/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author clril
 */
@Entity
public class Origin implements Serializable {
    @JsonBackReference("origin")
//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "origin")
    private WorkEntity work;

    public WorkEntity getWork() {
        return work;
    }

    public void setWork(WorkEntity work) {
        this.work = work;
    }
    
    
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    
    @OneToMany
//            @LazyCollection(LazyCollectionOption.FALSE)
    Set<SecondaryName> secondaryNames= new HashSet<>();
    
    
    String label;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SecondaryName> getSecondaryNames() {
        return secondaryNames;
    }

    public void setSecondaryNames(Set<SecondaryName> secondaryNames) {
        this.secondaryNames = secondaryNames;
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
        if (!(object instanceof Origin)) {
            return false;
        }
        Origin other = (Origin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.Origin[ id=" + id + " ]";
    }
    
}
