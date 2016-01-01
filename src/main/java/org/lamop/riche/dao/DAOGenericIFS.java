/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author clril
 * @param <T>
 */
public interface DAOGenericIFS<T> {

    public T getEntity(Long id);

    public void addEntity(T obj);

    public void removeEntity(T obj);

    public List<T> getAllEntities();

//    public T find(Long id);

    public T update(T obj);

    public List<T> find(String arg);
    
    
        public SessionFactory getSessionFactory();
        public void clearSession();

}
