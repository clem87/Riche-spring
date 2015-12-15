/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAORelationSourcePersonIfs;
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationSourcePerson;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
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
        Set<RelationSourcePerson> list = entity.getRelationPerson();
        for (Iterator<RelationSourcePerson> iterator = list.iterator(); iterator.hasNext();) {
            RelationSourcePerson get = iterator.next();

//        }
//        for (int i = 0; i < list.size(); i++) {
//            RelationSourcePerson get = list.get(i);
            Person person = daoPerson.getEntity(get.getPerson().getId());
            get.setPerson(person);
            get.setSource(entity);
        }
        dao.addEntity(entity);
    }

    @Transactional
    @Override
    public void removeEntity(Source source) {

        // Pour éviter le work recré la relation par cascade, il faut explicitement supprimer
        Set<RelationWorkSource> listRelation = source.getRelationWorkSource();
        for (Iterator<RelationWorkSource> iterator = listRelation.iterator(); iterator.hasNext();) {
            RelationWorkSource relation = iterator.next();
            daoRelationWorkSource.removeEntity(relation);

        }

        dao.removeEntity(source);
    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        Source s = getEntity(id);
        Set<RelationWorkSource> list = s.getRelationWorkSource();
        
        for (Iterator<RelationWorkSource> iterator = list.iterator(); iterator.hasNext();) {
            RelationWorkSource next = iterator.next();
            daoRelationWorkSource.removeEntity(next);
            
        }
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
        sourceExistante.setBibliographicType(entity.getBibliographicType());

        Set<RelationSourcePerson> relationsSendByUsers = entity.getRelationPerson();
        List<RelationSourcePerson> listAAjouter = new ArrayList<>();
        for (Iterator<RelationSourcePerson> iterator = relationsSendByUsers.iterator(); iterator.hasNext();) {
            RelationSourcePerson relationUser = iterator.next();

//        }
//        
//        for (ListIterator<RelationSourcePerson> iterator =  relationsSendByUsers.listIterator(); iterator.hasNext();) {
//            RelationSourcePerson relationUser = iterator.next();
            if (relationUser.getId() == null) {
                relationUser.setSource(sourceExistante);
                Person p = daoPerson.getEntity(relationUser.getPerson().getId());
                relationUser.setPerson(p);
            } else {
                RelationSourcePerson relation = daoRelationSourcePerson.getEntity(relationUser.getId());
                relationsSendByUsers.remove(relationUser);
                listAAjouter.add(relation);
//                iterator.set(relation);
            }
        }
        relationsSendByUsers.addAll(listAAjouter);
        sourceExistante.getRelationPerson().clear();
        sourceExistante.getRelationPerson().addAll(relationsSendByUsers);
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
        List<Source> dtoList = new ArrayList<>();
        List<Source> entitiesList = dao.find(arg);
        //TODO : ce serait mieu de faire un vrai DTO
//        for (int i = 0; i < entitiesList.size(); i++) {
//            Source get = entitiesList.get(i);
//            get.setRelationPerson(null);
//            get.setRelationWorkSource(null);
//        }
        return entitiesList;
    }

    public DAOSourceIfs getDao() {
        return dao;
    }

    public void setDao(DAOSourceIfs dao) {
        this.dao = dao;
    }

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

    
    @Override
    @Transactional
    public Long getAllCount() {
        return dao.getAllEntitiesCount();
    }

}