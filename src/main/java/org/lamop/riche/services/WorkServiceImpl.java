/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAOWorkIFS;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.WorkEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clril
 */
public class WorkServiceImpl implements WorkServiceIfs {

    @Autowired
    DAOWorkIFS dao;
    
    @Autowired
    DAOPersonIfs daoPerson;

    @Override
    public List<WorkEntity> getAll() {
        return dao.getAllEntities();
//        return null;
    }

    @Override
    public void addEntity(WorkEntity w) {
        //TODO : verification présence
       
        
        for (Iterator<Person> iterator = w.getAuthors().iterator(); iterator.hasNext();) {
            Person next = iterator.next();
            if(next.getId()==null){
                System.out.println("ADDING PERSON");
                daoPerson.addEntity(next);
            }
        }
        
        dao.addEntity(w);
    }

    @Override
    public void removeEntity(WorkEntity w) {
        dao.removeEntity(w);
    }

    
        @Override
    public void removeEntity(Long w  ) {
             WorkEntity wo = getWork(w);
        removeEntity(wo);
    }

    public WorkEntity getWork(Long id) {
        return dao.find(id);
    }

    public DAOWorkIFS getDao() {
        return dao;
    }

    public void setDao(DAOWorkIFS dao) {
        this.dao = dao;
    }



    @Override
    public void modifyEntity(WorkEntity entity) {
        
        dao.update(entity);
        
        
             
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DAOPersonIfs getDaoPerson() {
        return daoPerson;
    }

    public void setDaoPerson(DAOPersonIfs daoPerson) {
        this.daoPerson = daoPerson;
    }

    @Override
    public WorkEntity getEntity(Long id) {
        return dao.getEntity(id);
    }



    
    
    
    
}
