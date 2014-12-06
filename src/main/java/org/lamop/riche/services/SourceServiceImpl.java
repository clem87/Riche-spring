/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clril
 */
public class SourceServiceImpl implements SourceServiceIfs {

    @Autowired
    DAOSourceIfs dao;

    @Autowired
    DAOWorkIFS daoWork;

    Logger log = LoggerFactory.getLogger(SourceServiceImpl.class);
    @Override
    public List<Source> getAll() {
        return dao.getAllEntities();
    }

    @Override
    public void addEntity(Source entity) {
        dao.addEntity(entity);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(Source source) {
        EntityManager em = dao.getEm();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            
            source = em.find(Source.class, source.getId());
//            Query q = em.createQuery("SELECT s FROM Source s LEFT JOIN FETCH s.relationWorkSource r WHERE s.id=:idsource");
//            q.setParameter("idsource", entity.getId());
//            entity = (Source) q.getSingleResult();

            List<RelationWorkSource> listRealtion = source.getRelationWorkSource();
            List<WorkEntity> workaMerger = new ArrayList<>();
            
            for (int i = 0; i < listRealtion.size(); i++) {    
                RelationWorkSource relation = listRealtion.get(i);
                workaMerger.add(relation.getWorkEntity());
                relation.getWorkEntity().removeRelationWorkSource(relation);
                em.remove(relation);
//                removeRelation(relation);
            }
            for (int i = 0; i < workaMerger.size(); i++) {
                WorkEntity get = workaMerger.get(i);
                em.merge(get);
            }
            
            
            em.remove(source);
//            dao.removeEntity(entity);
            em.getTransaction().commit();

        } catch (Exception e) {
            log.error("Error during removing", e);
        } finally {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }

    }

    @Override
    public void removeEntity(Long id) {
        Source s = getEntity(id);
        removeEntity(s);
    }

    @Override
    public void modifyEntity(Source entity) {
        dao.update(entity);
    }

    @Override
    public Source getEntity(Long id) {
        return dao.getEntity(id);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Source> find(String arg) {
        return dao.find(arg);
    }

    public DAOSourceIfs getDao() {
        return dao;
    }

    public void setDao(DAOSourceIfs dao) {
        this.dao = dao;
    }

//    public void removeRelation(RelationWorkSource relation) {
//        Source source = relation.getSource();
//        source.removeRelationWorkSource(relation);
//
//        WorkEntity work = relation.getWorkEntity();
//        work.removeRelationWorkSource(relation);
//
//        EntityManager em = dao.getEm();
//
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//
//        em.remove(relation);
//        em.merge(work);
//        em.merge(source);
//
//    }

}
