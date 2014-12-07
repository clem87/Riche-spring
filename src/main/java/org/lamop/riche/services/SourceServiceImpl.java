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
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
@Service
//@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class SourceServiceImpl implements SourceServiceIfs {

    @Autowired
    DAOSourceIfs dao;

    @Autowired
    DAOWorkIFS daoWork;

    Logger log = LoggerFactory.getLogger(SourceServiceImpl.class);

    @Autowired
    DAORelationWorkSourceIfs daoRelationWorkSource;

    @Transactional
    @Override
    public List<Source> getAll() {
        return dao.getAllEntities();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addEntity(Source entity) {

        dao.addEntity(entity);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Transactional
    @Override
    public void removeEntity(Source source) {

        List<RelationWorkSource> listRelation = source.getRelationWorkSource();

        for (int i = 0; i < listRelation.size(); i++) {
            RelationWorkSource relation = listRelation.get(i);
            WorkEntity w = relation.getWorkEntity();

            if (w != null) {
                w.removeRelationWorkSource(relation);
                daoWork.update(w);
            }
            
            daoRelationWorkSource.removeEntity(relation);

        }

        dao.removeEntity(source);

    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        Source s = getEntity(id);
        removeEntity(s);
    }

    @Transactional
    @Override
    public void modifyEntity(Source entity) {
        dao.update(entity);
    }

    @Transactional
    @Override
    public Source getEntity(Long id) {
        return dao.getEntity(id);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
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


    public DAOWorkIFS getDaoWork() {
        return daoWork;
    }

    public void setDaoWork(DAOWorkIFS daoWork) {
        this.daoWork = daoWork;
    }

    public DAORelationWorkSourceIfs getDaoRelationWorkSource() {
        return daoRelationWorkSource;
    }

    public void setDaoRelationWorkSource(DAORelationWorkSourceIfs daoRelationWorkSource) {
        this.daoRelationWorkSource = daoRelationWorkSource;
    }
    
    
    

}
