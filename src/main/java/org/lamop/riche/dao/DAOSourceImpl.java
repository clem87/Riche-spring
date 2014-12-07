/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
@Service
@Repository
public class DAOSourceImpl extends DAOGenericImpl<Source> implements DAOSourceIfs{



//    @Override
//    public Source update(Source obj) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<Source> find(String arg) {
        Source s ;
        
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Source.class);
        criteria.add(Restrictions.like("title", "%"+arg+"%"));
        return criteria.list();

    }

    @Override
    public void addRelation(RelationWorkSource get) {
//        initEm();
//        em.getTransaction().begin();
//        em.persist(get);
//        em.getTransaction().commit();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public List<Source> getAllEntities() {
//        sessionFactory.getCurrentSession().flush();
//        sessionFactory.getCurrentSession().
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT s FROM Source s");
        List result = q.list();
        System.out.println("NOMBRE DE RESULT " + result.size());
        return result;
//        return super.getAllEntities(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
