/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;

/**
 *
 * @author clril
 */
public class DAOSourceImpl extends DAOGenericImpl<Source> implements DAOSourceIfs{

    @Override
    public Source find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Source update(Source obj) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<Source> find(String arg) {
          initEm();
        Query q = em.createQuery("SELECT s FROM Source s WHERE s.title LIKE :label");
        
        q.setParameter("label", "%"+arg+"%");
        return q.getResultList();
    }

    @Override
    public void addRelation(RelationWorkSource get) {
        initEm();
        em.getTransaction().begin();
        em.persist(get);
        em.getTransaction().commit();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
