/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOOrigineIFS;
import org.lamop.riche.model.Origin;

/**
 *
 * @author clril
 */
public class OrigineServiceImpl implements OrigineServiceIfs{
    
    DAOOrigineIFS dao;
    
    
    @Override
    public List<Origin> getAll() {
        return dao.getAllEntities();
    }

    @Override
    public void addEntity(Origin entity) {
        dao.addEntity(entity);
    }

    @Override
    public void removeEntity(Origin entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyEntity(Origin entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Origin getEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DAOOrigineIFS getDao() {
        return dao;
    }

    public void setDao(DAOOrigineIFS dao) {
        this.dao = dao;
    }
    
    
    
}
