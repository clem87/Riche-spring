/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOPersonIfs;
import org.lamop.riche.dao.DAOSourceIfs;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationSourcePerson;
import org.lamop.riche.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class AuthorServiceImpl implements PersonServiceIfs{

  @Autowired
    DAOPersonIfs dao;
  
  @Autowired
    DAOSourceIfs daoSource;
    

  @Transactional
    @Override
    public List<Person> find(String arg) {
        return dao.find(arg);
    }

    @Override
    public List<Person> getAll() {
        return dao.getAllEntities();
    }

      @Transactional
    @Override
    public void addEntity(Person entity) {
        dao.addEntity(entity);
    }

    @Transactional
    @Override
    public void removeEntity(Person entity) {
        dao.removeEntity(entity);
    }

    @Transactional
    @Override
    public void removeEntity(Long id) {
        Person p = getEntity(id);
        //Pour éviter de recrer la relation par cascade , il faut modifier manuellement les sources
        List<RelationSourcePerson> list = p.getRelationSource();
        for (int i = 0; i < list.size(); i++) {
            RelationSourcePerson get = list.get(i);
            Source s = get.getSource();
            s.removeRelationPerson(get);
            daoSource.update(s);
        }    
        dao.removeEntity(p);
    }

    @Transactional
    @Override
    public void modifyEntity(Person entity) {
        Person personInDB = getEntity(entity.getId());
        personInDB.setLabel(entity.getLabel());
        personInDB.setType(entity.getType());
        //TODO : ok pas de controle de valeur ...
        dao.update(personInDB);
    }

    public DAOPersonIfs getDao() {
        return dao;
    }

    public void setDao(DAOPersonIfs dao) {
        this.dao = dao;
    }

      @Transactional
    @Override
    public Person getEntity(Long id) {
        return dao.getEntity(id);
    }

    public DAOSourceIfs getDaoSource() {
        return daoSource;
    }

    public void setDaoSource(DAOSourceIfs daoSource) {
        this.daoSource = daoSource;
    }
}
