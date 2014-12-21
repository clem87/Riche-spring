/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOWorkAuthorIfs;
import org.lamop.riche.model.WorkAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class WorkAuthorServiceImpl implements WorkAuthorServiceIfs{

    
    @Autowired
    DAOWorkAuthorIfs dao;
    
    @Override
    @Transactional
    public List<WorkAuthor> getAll() {
        return dao.getAllEntities();
    }

    @Override
    @Transactional
    public void addEntity(WorkAuthor entity) {
        dao.addEntity(entity);
    }

    @Override
    @Transactional
    public void removeEntity(WorkAuthor entity) {
        dao.removeEntity(entity);
    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        WorkAuthor wa = getEntity(id);
        removeEntity(wa);
    }

    @Override
    @Transactional
    public void modifyEntity(WorkAuthor entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public WorkAuthor getEntity(Long id) {
        return dao.getEntity(id);
    }

    public DAOWorkAuthorIfs getDao() {
        return dao;
    }

    public void setDao(DAOWorkAuthorIfs dao) {
        this.dao = dao;
    }
    
    
    
}
