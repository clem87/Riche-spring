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
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.model.WorkEntityDTO;
import org.lamop.riche.model.search.SearchBean;
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
    public List<WorkEntityDTO> getAllDTO() {
        List<WorkEntity> listWorks = getAll();
        List<WorkEntityDTO> listToReturn = new ArrayList<>();
        for (int i = 0; i < listWorks.size(); i++) {
            WorkEntity get = listWorks.get(i);
            WorkEntityDTO dto = new WorkEntityDTO();
            dto.feedWithEntity(get);
            listToReturn.add(dto);
        }
        return listToReturn;
    }

    @Override
    public List<WorkEntity> getAll() {
        return dao.getAllEntities();
    }

    @Transactional
    @Override
    public Long getAllCount() {
        return dao.getAllEntitiesCount();
    }

    @Transactional
    @Override
    public void addEntity(WorkEntity w) {
        Set<RelationWorkSource> listRelation = w.getRelationWorkSource();
        for (Iterator<RelationWorkSource> iterator = listRelation.iterator(); iterator.hasNext();) {
            RelationWorkSource relation = iterator.next();
            Source s = daoSource.getEntity(relation.getSource().getId());
            relation.setSource(s);
            relation.setWorkEntity(w);
        }
        dao.addEntity(w);
    }

    @Transactional
    @Override
    public void removeEntity(WorkEntity w) {
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

        for (Iterator<RelationWorkSource> iterator = rel.iterator(); iterator.hasNext();) {
            RelationWorkSource relationByUser = iterator.next();

            if (relationByUser.getId() == null) {
                Source s = daoSource.getEntity(relationByUser.getSource().getId());
                relationByUser.setSource(s);
                relationByUser.setWorkEntity(workinDB);
            } else {
                RelationWorkSource relInDB = daoRelationWorkSource.getEntity(relationByUser.getId());
                add.add(relInDB);
            }
        }
        rel.addAll(add);
        workinDB.getRelationWorkSource().clear();
        workinDB.getRelationWorkSource().addAll(entity.getRelationWorkSource());
        dao.update(workinDB);
    }

    public void removeRelation(RelationWorkSource relation) {
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
        if (author != null) {
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
        }
        return null;
    }

    public WorkAuthorServiceIfs getWorkAuthorService() {
        return workAuthorService;
    }

    public void setWorkAuthorService(WorkAuthorServiceIfs workAuthorService) {
        this.workAuthorService = workAuthorService;
    }

    @Override
    @Transactional
    public List<WorkEntityDTO> search(SearchBean search) {

        List<WorkEntity> entites = dao.search(search);
        List<WorkEntityDTO> dtos = new ArrayList<>();
        for (int i = 0; i < entites.size(); i++) {
            WorkEntity get = entites.get(i);

            WorkEntityDTO dto = new WorkEntityDTO();
            dto.feedWithEntity(get);
            dtos.add(dto);
        }
        return dtos;
    }

}
