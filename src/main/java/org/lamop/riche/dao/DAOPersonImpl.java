/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.lamop.riche.model.Person;

/**
 *
 * @author clril
 */
public class DAOPersonImpl extends DAOGenericImpl<Person> implements DAOPersonIfs{
    
//    private static String REQ_FIND = ""
    
    public List<Person> find(String arg){
        
        initEm();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.label LIKE :label");
        
        q.setParameter("label", "%"+arg+"%");
        return q.getResultList();
        
    }

    @Override
    public Person find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person update(Person obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
