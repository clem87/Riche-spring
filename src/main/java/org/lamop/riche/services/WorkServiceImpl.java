/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import javax.persistence.EntityManager;
import org.lamop.riche.dao.DAOGenericImpl;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkAuthor;
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
    
    @Autowired
    WorkAuthorServiceIfs workAuthorService;

    Logger log = LoggerFactory.getLogger(WorkServiceImpl.class);

    @Override
    public List<WorkEntity> getAll() {

        return dao.getAllEntities();
//        return null;
    }

    @Transactional
    @Override
    public void addEntity(WorkEntity w) {

     
        Set<RelationWorkSource> listRelation = w.getRelationWorkSource();
        for (Iterator<RelationWorkSource> iterator = listRelation.iterator(); iterator.hasNext();) {
            RelationWorkSource relation = iterator.next();
            
//        }
//
//        for (int i = 0; i < listRelation.size(); i++) {
//            RelationWorkSource relation = listRelation.get(i);
            Source s = daoSource.getEntity(relation.getSource().getId());
//            s.addRelationWorkSource(relation);
            relation.setSource(s);
            relation.setWorkEntity(w);
//            daoRelationWorkSource.addEntity(relation);
        }
           dao.addEntity(w);

    }

    @Transactional
    @Override
    public void removeEntity(WorkEntity w) {

//        List<RelationWorkSource> listRelation = daoRelationWorkSource.findRelationForSource(w);
//        for (Iterator<RelationWorkSource> iterator = listRelation.iterator(); iterator.hasNext();) {
//            RelationWorkSource relation = iterator.next();
//            Source s = relation.getSource();
//            s.removeRelationWorkSource(relation);
//            daoSource.update(s);
//            daoRelationWorkSource.removeEntity(relation);
//        }
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

        WorkEntity workinDB = dao.getEntity(entity.getId());
        workinDB.setCenturyMax(entity.getCenturyMax());
        workinDB.setCenturyMin(entity.getCenturyMin());
        workinDB.setExactDate(entity.getExactDate());
        workinDB.setOrigin(entity.getOrigin());
        workinDB.setTheme(entity.getTheme());
        workinDB.setTitle(entity.getTitle());
        
        workinDB.setAuthors(entity.getAuthors());
        workinDB.setNote(entity.getNote());

        
        
        
        Set<RelationWorkSource> rel = entity.getRelationWorkSource();
        List<RelationWorkSource> add = new ArrayList<>();
      
        for (Iterator<RelationWorkSource> iterator = rel. iterator(); iterator.hasNext();) {
            RelationWorkSource relationByUser = iterator.next();
            
//        }
//        for (ListIterator<RelationWorkSource> iterator = rel.listIterator(); iterator.hasNext();) {
//            RelationWorkSource relationByUser = iterator.next();
            if(relationByUser.getId()==null){
                Source s = daoSource.getEntity(relationByUser.getSource().getId());
                relationByUser.setSource(s);
                relationByUser.setWorkEntity(workinDB);
            }
            else{
                RelationWorkSource relInDB= daoRelationWorkSource.getEntity(relationByUser.getId());
//                iterator.set(relInDB);
                add.add(relInDB);
            }
        }
        rel.addAll(add);
        
        workinDB.getRelationWorkSource().clear();
        workinDB.getRelationWorkSource().addAll(entity.getRelationWorkSource());
        
        
        
        
        
        
//        
//        List<RelationWorkSource> listrelationEnBase = workinDB.getRelationWorkSource();
//         List<RelationWorkSource> listRelationSelectionUser = entity.getRelationWorkSource();
//        for (int i = 0; i < listrelationEnBase.size(); i++) {
//            RelationWorkSource relationEnBase = listrelationEnBase.get(i);
//            
//            if(!listRelationSelectionUser.contains(relationEnBase)){
//                listrelationEnBase.remove(relationEnBase);
//                relationEnBase.getSource().removeRelationWorkSource(relationEnBase);
//                daoSource.update(relationEnBase.getSource());
//                daoRelationWorkSource.removeEntity(relationEnBase);
//            }
//        }
//        for (int i = 0; i < listRelationSelectionUser.size(); i++) {
//            RelationWorkSource relationUser = listRelationSelectionUser.get(i);
//            if(!listrelationEnBase.contains(relationUser)){
//                listrelationEnBase.add(relationUser);
//                daoRelationWorkSource.addEntity(relationUser);
//                Source source = daoSource.getEntity(relationUser.getSource().getId());
//                source.addRelationWorkSource(relationUser);
//                daoSource.update(source);
//            }
//        }

        dao.update(workinDB);

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
//        entity.getRelationWorkSource();
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

    
    @Override
    @Transactional
    public List<WorkEntity> getWorkForAuthor(long id) {
        
        WorkAuthor author = workAuthorService.getEntity(id);
        if(author != null){
            List<WorkEntity> listWork = dao.getWorkForAuthor(author);
            List<WorkEntity> listReturn = new ArrayList<>();
            for (int i = 0; i < listWork.size(); i++) {
                WorkEntity get = listWork.get(i);
                WorkEntity clone = new WorkEntity();
                clone.setId(get.getId());
                clone.setTitle(get.getTitle());
                listReturn.add(clone);
            }
            
            return listReturn;
//            return dao.getWorkForAuthor(author);
        }
        return null;
    }

    public WorkAuthorServiceIfs getWorkAuthorService() {
        return workAuthorService;
    }

    public void setWorkAuthorService(WorkAuthorServiceIfs workAuthorService) {
        this.workAuthorService = workAuthorService;
    }
    
    

}
