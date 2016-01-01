/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.WorkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 * @param <T>
 */
@Repository
public abstract class DAOGenericImpl<T> implements DAOGenericIFS<T> {

    Logger log = LoggerFactory.getLogger(DAOGenericImpl.class);

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public DAOGenericImpl() {
        persistentClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), DAOGenericImpl.class);
    }

    @Override
    public T getEntity(Long id) {
       return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
        
    }


    public WorkEntity getEntity(T id) {
        return null;
    }

    @Transactional
    @Override
    public void addEntity(T obj) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
        sessionFactory.getCurrentSession().save(obj);
    }
    
    
    @Override
    @Transactional
    public void clearSession(){
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void removeEntity(T obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    
    @Transactional
    @Override
    public List<T> getAllEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        return criteria.list();
    }

    @Transactional
    @Override
    public T update(T obj) throws IllegalArgumentException, TransactionRequiredException {
        Object retour = sessionFactory.getCurrentSession().merge(obj);
        return (T) retour;
    }

    @Override
    public List<T> find(String arg) {
       Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.like("label", "%"+arg+"%"));
        return criteria.list();
        
        
//        sessionFactory.getCurrentSession().create
//        
//        throw new UnsupportedOperationException();
//        return null;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
