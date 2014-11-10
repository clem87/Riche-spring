/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.lamop.riche.model.WorkEntity;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<WorkEntity> getAllEntities() {
        initEm();
        Query q = em.createQuery("SELECT w FROM WorkEntity w");
        return q.getResultList();
//        return super.getAllEntities(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WorkEntity find(Long id) {
        initEm();
        return em.find(WorkEntity.class, id);
    }

    @Override
    public WorkEntity update(WorkEntity obj) {
        initEm();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.merge(obj);
        em.getTransaction().commit();
        return obj;

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
