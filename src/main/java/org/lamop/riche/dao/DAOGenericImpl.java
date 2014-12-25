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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.Person;
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

//    @Autowired
//    protected EntityManagerFactory emf;
//
//    protected EntityManager em;

    private Class<T> persistentClass;

    public DAOGenericImpl() {
        persistentClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), DAOGenericImpl.class);
    }

    public T getEntity(Long id) {
       return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
        
    }

    @PersistenceContext
    private EntityManager entityManager;

    public WorkEntity getEntity(T id) {
        return null;
    }

    @Transactional
    @Override
    public void addEntity(T obj) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {


        sessionFactory.getCurrentSession().save(obj);
 
     
//        tr.commit();

//        try {
//            initEm();
//
//            if (!em.getTransaction().isActive()) {
//                em.getTransaction().begin();
//            }
//            em.persist(obj);
//            
//            em.getTransaction().commit();
//
//        } catch (Exception e) {
//            log.error("Error during adding ", e);
//            throw e;
//        } finally {
//            finallyCloseEmTransaction(em);
//        }
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

//        initEm();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> query = cb.createQuery(persistentClass);
//        Root<T> root = query.from(persistentClass);
//        query.select(root);
//        Query q = em.createQuery(query);
//        return q.getResultList();
    }

    @Transactional
    @Override
    public T update(T obj) throws IllegalArgumentException, TransactionRequiredException {

        Object retour = sessionFactory.getCurrentSession().merge(obj);
//        sessionFactory.getCurrentSession().flush();
        return (T) retour;
//        try {
//            initEm();
//            if(!em.getTransaction().isActive()){
//                em.getTransaction().begin();
//            }
//            
//            em.merge(obj);
//            em.getTransaction().commit();
//            return obj;
//
//        } catch (Exception e) {
//            log.error("Erreur during update", e);
//            throw e;
//        } finally {
//            finallyCloseEmTransaction(em);
//        }
    }

//    public void initEm() {
//        if (em == null) {
//            em = emf.createEntityManager();
//        }
//
//    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    public EntityManagerFactory getEmf() {
//        return emf;
//    }
//
//    @PersistenceUnit
//    public void setEmf(EntityManagerFactory emf) {
//        this.emf = emf;
//    }

//    public EntityManager getEm() {
//        return em;
//    }

//    public void setEm(EntityManager em) {
//        this.em = em;
//    }

//    @Override
//    public T find(Long id){
//        
//        
//        return null;
//    };

    
    
    
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

    private void finallyCloseEmTransaction(EntityManager em) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
