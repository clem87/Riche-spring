/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
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

        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w "
                + "LEFT JOIN FETCH w.authors authors "
                + "LEFT JOIN FETCH w.relationWorkSource relation "
                + "LEFT JOIN FETCH w.theme theme");
        
        
        List<WorkEntity> returnList = q.list();
//        for (int i = 0; i < returnList.size(); i++) {
//            WorkEntity get = returnList.get(i);
//            
////            sessionFactory.getCurrentSession().evict(get);
////            Hibernate.initialize(get);
//        }
        sessionFactory.getCurrentSession().clear();
        

        return returnList;

//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkEntity.class);
//        criteria.setFetchMode("relationWorkSource", FetchMode.JOIN);
////         criteria.setFetchMode("authors", FetchMode.JOIN);
////         criteria.setFetchMode("theme", FetchMode.JOIN);
//        return criteria.list();

    }


    @Override
    public WorkEntity getEntity(Long id) {
                org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w "
                + "LEFT JOIN FETCH w.authors authors "
//                + "LEFT JOIN FETCH w.origin origin "
                + "LEFT JOIN FETCH w.relationWorkSource relation "
                + "LEFT JOIN FETCH w.theme theme "
                + "WHERE w.id=:id");
        q.setParameter("id", id);
        WorkEntity result = (WorkEntity) q.uniqueResult();
         sessionFactory.getCurrentSession().clear();
         return result;
    }
    
    
    
    
    

}
