/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.lamop.riche.model.WorkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

/**
 *
 * @author clril
 * @param <T>
 */
@Repository
public abstract class DAOGenericImpl<T> implements DAOGenericIFS<T> {

    @Autowired
    protected EntityManagerFactory emf;

    protected EntityManager em;
    
    private Class<T> persistentClass;

    public DAOGenericImpl() {
        persistentClass =  (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), DAOGenericImpl.class);
    }
    
       
    


    public T getEntity(Long id) {
        initEm();
        return em.find( persistentClass, id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public WorkEntity getEntity(T id) {
        return null;
    }

    public void addEntity(T obj) {
        initEm();
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();            
        }

        em.persist(obj);
        em.getTransaction().commit();
    }

    ;
    public void removeEntity(T obj) {

        initEm();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

        em.remove(obj);
        em.getTransaction().commit();
    }

    ;
    public List<T> getAllEntities() {
        return null;
    }

    protected void initEm() {
        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
