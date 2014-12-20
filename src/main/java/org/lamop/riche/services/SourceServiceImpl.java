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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAORelationSourcePersonIfs;
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationSourcePerson;
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

    @Autowired
    DAOPersonIfs daoPerson;
//    @Autowired
//    DAOSourceIfs daoSource;

    @Autowired
    DAORelationSourcePersonIfs daoRelationSourcePerson;

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
        
         
        List<RelationSourcePerson> list = entity.getRelationPerson();
        for (int i = 0; i < list.size(); i++) {
            RelationSourcePerson get = list.get(i);
           Person person=  daoPerson.getEntity(get.getPerson().getId());
           get.setPerson(person);
            get.setSource(entity);
//           daoRelationSourcePerson.addEntity(get);
            
        }
         dao.addEntity(entity);

       
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void removeEntity(Source source) {

        List<RelationWorkSource> listRelation = source.getRelationWorkSource();

//        for (int i = 0; i < listRelation.size(); i++) {
//            RelationWorkSource relation = listRelation.get(i);
//            WorkEntity w = relation.getWorkEntity();
////
//            if (w != null) {
//                w.removeRelationWorkSource(relation);
//                daoWork.update(w);
//            }
//
//            daoRelationWorkSource.removeEntity(relation);
//
////            daoRelationWorkSource.update(relation);
////
//        }

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

        Source sourceExistante = dao.getEntity(entity.getId());
        sourceExistante.setArticleTitle(entity.getArticleTitle());
        sourceExistante.setEditor(entity.getEditor());
        sourceExistante.setNum(entity.getNum());
        sourceExistante.setPublisher(entity.getPublisher());
        sourceExistante.setReleaseTown(entity.getReleaseTown());
        sourceExistante.setReleaseYear(entity.getReleaseYear());
        sourceExistante.setSeries(entity.getSeries());
        sourceExistante.setTitle(entity.getTitle());
        sourceExistante.setVolume(entity.getVolume());

        sourceExistante.setAuthors(entity.getAuthors());
        sourceExistante.setBibliographicType(entity.getBibliographicType());
        
        
        List<RelationSourcePerson> relationsSendByUsers = entity.getRelationPerson();
        
  
        
        for (ListIterator<RelationSourcePerson> iterator =  relationsSendByUsers.listIterator(); iterator.hasNext();) {
            RelationSourcePerson relationUser = iterator.next();
            
        
            if(relationUser.getId()==null){
                relationUser.setSource(sourceExistante);
                Person p = daoPerson.getEntity(relationUser.getPerson().getId());
                relationUser.setPerson(p);
            }
            else{
                RelationSourcePerson relation = daoRelationSourcePerson.getEntity(relationUser.getId());
          iterator.set(relation);
            }
        }
        sourceExistante.getRelationPerson().clear();
        sourceExistante.getRelationPerson().addAll(relationsSendByUsers);
        
        
        
        
        //---SANS CASCADE :


//        for (int i = 0; i < sourceExistante.getRelationPerson().size(); i++) {
//            RelationSourcePerson get = sourceExistante.getRelationPerson().get(i);
//            if (!entity.getRelationPerson().contains(get)) {
//                Person p = daoPerson.getEntity(get.getPerson().getId());
//                p.removeRelationSourcePerson(get);
//                daoPerson.update(p);
//                daoRelationSourcePerson.removeEntity(get);
//            }
//        }

//        List<RelationSourcePerson> relationSourcePersonsSendByUser = entity.getRelationPerson();

//        for (int i = 0; i < relationSourcePersonsSendByUser.size(); i++) {
//            RelationSourcePerson relationSendedByUser = relationSourcePersonsSendByUser.get(i);
//            if (relationSendedByUser.getId() == null) {
//                Person person = daoPerson.getEntity(relationSendedByUser.getPerson().getId());
//                relationSendedByUser.setPerson(person);
//                relationSendedByUser.setSource(sourceExistante);
//                daoRelationSourcePerson.addEntity(relationSendedByUser);
//                daoPerson.update(person);
//                sourceExistante.getRelationPerson().add(relationSendedByUser);
//            } else {
//                RelationSourcePerson relationInDB = daoRelationSourcePerson.getEntity(relationSendedByUser.getId());
//                sourceExistante.getRelationPerson().add(relationInDB);
//            }
//        }

        dao.update(sourceExistante);

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

    public DAOPersonIfs getDaoPerson() {
        return daoPerson;
    }

    public void setDaoPerson(DAOPersonIfs daoPerson) {
        this.daoPerson = daoPerson;
    }

    public DAORelationSourcePersonIfs getDaoRelationSourcePerson() {
        return daoRelationSourcePerson;
    }

    public void setDaoRelationSourcePerson(DAORelationSourcePersonIfs daoRelationSourcePerson) {
        this.daoRelationSourcePerson = daoRelationSourcePerson;
    }

}
