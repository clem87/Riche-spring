/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;

/**
 *
 * @author clril
 */
public interface ServiceCRUDIfs<T> {
    
    public List<T> getAll();
    public void addEntity(T entity);
    
    public void removeEntity(T entity);
    
    public void removeEntity(Long id);
    
    public void modifyEntity(T entity);
    
    public T getEntity(Long id);
    
    
}
