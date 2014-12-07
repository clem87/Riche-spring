/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import org.hibernate.SessionFactory;
import org.lamop.riche.model.Source;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class DAOTransationnelSource {
     private SessionFactory sessionFactory;

     

     public void createSource(Source s ){
         sessionFactory.openSession().persist(s);
     }
     
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
     
     
     
    
}
