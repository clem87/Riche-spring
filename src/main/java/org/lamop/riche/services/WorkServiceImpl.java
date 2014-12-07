/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import org.lamop.riche.dao.DAOGenericImpl;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class WorkServiceImpl implements WorkServiceIfs {

    @Autowired
    DAOWorkIFS dao;

    @Autowired
    DAOPersonIfs daoPerson;

    @Autowired
    DAOSourceIfs daoSource;

    @Autowired
    DAORelationWorkSourceIfs daoRelationWorkSource;

    Logger log = LoggerFactory.getLogger(WorkServiceImpl.class);

    @Override
    public List<WorkEntity> getAll() {

        return dao.getAllEntities();
//        return null;
    }

    @Transactional
    @Override
    public void addEntity(WorkEntity w) {
        //TODO : verification présence

        for (Iterator<Person> iterator = w.getAuthors().iterator(); iterator.hasNext();) {
            Person next = iterator.next();
            if (next.getId() == null) {
                System.out.println("ADDING PERSON");
                daoPerson.addEntity(next);
            }
        }

        dao.addEntity(w);
    }

    @Transactional
    @Override
    public void removeEntity(WorkEntity w) {
        List<RelationWorkSource> listRelation = daoRelationWorkSource.findRelationForSource(w);
        for (Iterator<RelationWorkSource> iterator = listRelation.iterator(); iterator.hasNext();) {
            RelationWorkSource relation = iterator.next();
            if (relation.getSource() != null) {
                relation.getSource().removeRelationWorkSource(relation);
                daoSource.update(relation.getSource());
            }
            daoRelationWorkSource.removeEntity(relation);
        }
        dao.removeEntity(w);
    }

    @Transactional
    @Override
    public void removeEntity(Long w) {
        WorkEntity wo = getWork(w);
        removeEntity(wo);
    }

    public WorkEntity getWork(Long id) {
        return dao.getEntity(id);
    }

    public DAOWorkIFS getDao() {
        return dao;
    }

    public void setDao(DAOWorkIFS dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void modifyEntity(WorkEntity entity) {

//        log.debug("Modify Work : " + entity.getId());
//        dao.initEm();
//        EntityManager em = dao.getEm();
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//
        List<RelationWorkSource> d = entity.getRelationWorkSource();
        for (int i = 0; i < d.size(); i++) {
            RelationWorkSource relation = d.get(i);
            if (relation.getId() == null) {
                log.debug("Adding relation");
                daoRelationWorkSource.addEntity(relation);

//                em.persist(relation);
                if (relation.getSource() != null) {
                    relation.getSource().addRelationWorkSource(relation);
                    daoSource.update(relation.getSource());
//                    em.merge(relation.getSource());
                }
            }
        }
        dao.update(entity);
//
//        dao.update(entity);

//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeRelation(RelationWorkSource relation) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public DAOPersonIfs getDaoPerson() {
        return daoPerson;
    }

    public void setDaoPerson(DAOPersonIfs daoPerson) {
        this.daoPerson = daoPerson;
    }

    @Transactional
    @Override
    public WorkEntity getEntity(Long id) {
        WorkEntity entity = dao.getEntity(id);
        entity.getRelationWorkSource();
        return entity;
    }

    public DAOSourceIfs getDaoSource() {
        return daoSource;
    }

    public void setDaoSource(DAOSourceIfs daoSource) {
        this.daoSource = daoSource;
    }

    public DAORelationWorkSourceIfs getDaoRelationWorkSource() {
        return daoRelationWorkSource;
    }

    public void setDaoRelationWorkSource(DAORelationWorkSourceIfs daoRelationWorkSource) {
        this.daoRelationWorkSource = daoRelationWorkSource;
    }

}
