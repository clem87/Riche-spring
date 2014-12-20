/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author clril
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
//        property = "@id", scope = RelationSourcePerson.class)
@JsonSerialize(using = RelationSourcePersonSerializer.class)
public class RelationSourcePerson implements Serializable {

    @ManyToOne()
     @LazyCollection(LazyCollectionOption.FALSE)
//    @JsonBackReference(value = "relationSource")
//    @JsonManagedReference
    private Person person;

    @ManyToOne()
     @LazyCollection(LazyCollectionOption.FALSE)
    private Source source;
    
    
    /***
     * Permet directeur de publication traducteur...
     */
    private String rolePublication;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRolePublication() {
        return rolePublication;
    }

    public void setRolePublication(String rolePublication) {
        this.rolePublication = rolePublication;
    }

  
    
    
}
