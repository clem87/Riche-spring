/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import java.util.Set;
import org.lamop.riche.dao.DAOWorkAuthorIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class WorkAuthorServiceImpl implements WorkAuthorServiceIfs{

    
    @Autowired
    DAOWorkAuthorIfs dao;
    
    DAOWorkIFS daoWork;
    
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
        
        List<WorkEntity> listWork = daoWork.getWorkForAuthor(wa);
        for (int i = 0; i < listWork.size(); i++) {
            WorkEntity get = listWork.get(i);
            Set<WorkAuthor> authors =  get.getAuthors();
            authors.remove(wa);
            daoWork.update(get);
        }
        
        
        //suppression de l'auteur parmis les Oeuvres
        
//        List<WorkEntity> listWork = wa.
        
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

    @Transactional
    @Override
    public List<WorkAuthor> find(String arg, boolean approx) {
        
        return dao.find(arg, approx);
    }

    public DAOWorkIFS getDaoWork() {
        return daoWork;
    }

    public void setDaoWork(DAOWorkIFS daoWork) {
        this.daoWork = daoWork;
    }
    
    

    
    
}
