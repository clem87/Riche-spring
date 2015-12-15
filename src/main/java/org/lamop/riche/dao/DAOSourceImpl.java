/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Query;

/**
 *
 * @author clril
 */
@Service
@Repository
public class DAOSourceImpl extends DAOGenericImpl<Source> implements DAOSourceIfs {

    @Transactional
    @Override
    public List<Source> find(String arg) { 
       Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT s FROM Source s LEFT JOIN FETCH s.relationWorkSource rws LEFT JOIN FETCH s.relationPerson rpers LEFT JOIN FETCH s.bibliographicType btype WHERE s.title LIKE '%"+arg+"%'");
       List result = q.list();
       sessionFactory.getCurrentSession().clear();
        return result;
    }
@Transactional
    @Override
    public void addRelation(RelationWorkSource get) {
//        initEm();
//        em.getTransaction().begin();
//        em.persist(get);
//        em.getTransaction().commit();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public List<Source> getAllEntities() {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT s FROM Source s LEFT JOIN FETCH s.relationWorkSource rws LEFT JOIN FETCH s.relationPerson rpers LEFT JOIN FETCH s.bibliographicType btype");
        List result = q.list();
        System.out.println("NOMBRE DE RESULT " + result.size());
        sessionFactory.getCurrentSession().clear();
        return result;
    }

    @Transactional
    @Override
    public List<Source> getAllSourcesForAuthor(Person p) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT s FROM Source s JOIN FETCH s.authors a WHERE a.id=:aid");
        q.setParameter("aid", p.getId());
        List result = q.list();
        sessionFactory.getCurrentSession().clear();
        return result;
    }

    @Transactional
    @Override
    public Source getEntity(Long id) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT s FROM Source s"
                + " LEFT JOIN FETCH s.relationWorkSource rws "
                + "LEFT JOIN FETCH s.relationPerson rpers LEFT "
                + "JOIN FETCH s.bibliographicType btype "
                + "WHERE s.id=:id");
        q.setParameter("id", id);
        Source resu = (Source) q.uniqueResult();
         sessionFactory.getCurrentSession().clear();
         return resu;
    }

    @Override
    public Long getAllEntitiesCount() {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Source");
        return (Long) q.uniqueResult();
    }
}
