/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.lamop.riche.model.WorkEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
@Repository
public class DAOWorkImpl extends DAOGenericImpl<WorkEntity> implements DAOWorkIFS {

    @Override
    public void testSpeWork(WorkEntity w) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public List<WorkEntity> getAllEntities() {
//        initEm();
//        Query q = em.createQuery("SELECT w FROM WorkEntity w");
//        return q.getResultList();
////        return super.getAllEntities(); //To change body of generated methods, choose Tools | Templates.
//    }
    @Transactional
    @Override
    public List<WorkEntity> getAllEntities() {
//        return super.getAllEntities(); //To change body of generated methods, choose Tools | Templates.

        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT w FROM WorkEntity w "
                + "JOIN FETCH w.authors authors "
                + "JOIN FETCH w.origin origin "
                + "JOIN FETCH w.relationWorkSource relation "
                + "JOIN FETCH w.theme theme");
        return q.list();

//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkEntity.class);
//        criteria.setFetchMode("relationWorkSource", FetchMode.JOIN);
////         criteria.setFetchMode("authors", FetchMode.JOIN);
////         criteria.setFetchMode("theme", FetchMode.JOIN);
//        return criteria.list();

    }

}
