/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOBibliographicTypeIfs;
import org.lamop.riche.model.BibliographicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class BibliographicTypeServiceImpl implements BibliographicTypeServiceIfs {

    @Autowired
    DAOBibliographicTypeIfs dao;

    @Transactional
    @Override
    public List<BibliographicType> getAll() {
        return dao.getAllEntities();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEntity(BibliographicType entity) {
        dao.addEntity(entity);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(BibliographicType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyEntity(BibliographicType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BibliographicType getEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DAOBibliographicTypeIfs getDao() {
        return dao;
    }

    public void setDao(DAOBibliographicTypeIfs dao) {
        this.dao = dao;
    }
    
    
    

}
