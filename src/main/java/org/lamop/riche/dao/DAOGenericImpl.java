/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.WorkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author clril
 * @param <T>
 */
@Repository
public abstract class DAOGenericImpl<T> implements DAOGenericIFS<T> {

    Logger log = LoggerFactory.getLogger(DAOGenericImpl.class);

    @Autowired
    protected EntityManagerFactory emf;

    protected EntityManager em;

    private Class<T> persistentClass;

    public DAOGenericImpl() {
        persistentClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), DAOGenericImpl.class);
    }

    public T getEntity(Long id) {
        initEm();
        return em.find(persistentClass, id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public WorkEntity getEntity(T id) {
        return null;
    }

    @Override
    public void addEntity(T obj) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {

        try {
            initEm();

            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(obj);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            log.error("Error during adding ", e);
            throw e;
        } finally {
            finallyCloseEmTransaction(em);
        }
    }

    ;
    @Override
    public void removeEntity(T obj) {

        initEm();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            em.remove(obj);
            em.getTransaction().commit();

        } catch (Exception e) {
            log.error("Error during removing", e);
        } finally {
            finallyCloseEmTransaction(em);
        }
    }

    ;
    public List<T> getAllEntities() {
//        Query q = em.createQuery(null);
        initEm();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        query.select(root);
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public T update(T obj) throws IllegalArgumentException, TransactionRequiredException {

        try {
            initEm();
            if(!em.getTransaction().isActive()){
                em.getTransaction().begin();
            }
            
            em.merge(obj);
            em.getTransaction().commit();
            return obj;

        } catch (Exception e) {
            log.error("Erreur during update", e);
            throw e;
        } finally {
            finallyCloseEmTransaction(em);
        }
    }

    public void initEm() {
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

    @Override
    public List<T> find(String arg) {
        throw new UnsupportedOperationException();
//        return null;
    }

    private void finallyCloseEmTransaction(EntityManager em) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

    }

}
