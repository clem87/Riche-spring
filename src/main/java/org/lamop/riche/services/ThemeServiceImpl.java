/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOThemeIfs;
import org.lamop.riche.model.Theme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author clril
 */
public class ThemeServiceImpl implements ThemeServiceIfs{

    @Autowired
    DAOThemeIfs dao;
    
    @Override
    public List<Theme> getAll() {
        return dao.getAllEntities();
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEntity(Theme entity) {
        dao.addEntity(entity);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyEntity(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Theme getEntity(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DAOThemeIfs getDao() {
        return dao;
    }

    public void setDao(DAOThemeIfs dao) {
        this.dao = dao;
    }
    
    
    
    
    
}
