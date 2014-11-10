/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import java.io.Serializable;
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
public class AuthorityNotice implements Serializable {


    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String noticeRef;

    @ManyToOne
    AuthorityOrganism organisme;

    /**
     * Get the value of noticeRef
     *
     * @return the value of noticeRef
     */
    public String getNoticeRef() {
        return noticeRef;
    }

    /**
     * Set the value of noticeRef
     *
     * @param noticeRef new value of noticeRef
     */
    public void setNoticeRef(String noticeRef) {
        this.noticeRef = noticeRef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityOrganism getOrganisme() {
        return organisme;
    }

    public void setOrganisme(AuthorityOrganism organisme) {
        this.organisme = organisme;
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
        if (!(object instanceof AuthorityNotice)) {
            return false;
        }
        AuthorityNotice other = (AuthorityNotice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lamop.riche.model.AuthorityNotice[ id=" + id + " ]";
    }
    
    

}
