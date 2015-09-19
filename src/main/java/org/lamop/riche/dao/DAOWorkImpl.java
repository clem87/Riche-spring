/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.WorkAuthor;
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


    @Transactional
    @Override
    public List<WorkEntity> getAllEntities() {

        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w "
                + "LEFT JOIN FETCH w.authors authors "
                + "LEFT JOIN FETCH w.relationWorkSource relation "
                + "LEFT JOIN FETCH w.theme theme");
        
        List<WorkEntity> returnList = q.list();
        sessionFactory.getCurrentSession().clear();
        return returnList;
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

    /***
     * get all work for the author sended in param
     * @param author author used in where clause
     * @return list of work
     */
    @Override
    public List<WorkEntity> getWorkForAuthor(WorkAuthor author) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w JOIN w.authors au WHERE au.id = :id");
        q.setParameter("id", author.getId());
        List<WorkEntity> result = q.list();
        return result;
    }
}
