/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.Source;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class DAOPersonImpl extends DAOGenericImpl<Person> implements DAOPersonIfs {

//    private static String REQ_FIND = ""
    @Override
    public List<Person> find(String arg) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.notices notices LEFT JOIN FETCH p.relationSource relation WHERE p.label LIKE '%"+arg+"%'");

//        q.setString(0, arg);
//        q.setParameter("label", arg); 
            List resu = q.list();
        sessionFactory.getCurrentSession().clear();
        return resu;
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
//        criteria.add(Restrictions.like("label", "%" + arg + "%"));
//        List resu = criteria.list();
//        return resu;

    }

    @Transactional
    @Override
    public List<Person> getPersonFromLabel(String s) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.like("label", s));
        List resu = criteria.list();
        return resu;
    }

    @Transactional
    @Override
    public List<Person> getAllEntities() {
//        return super.getAllEntities(); //To change body of generated methods, choose Tools | Templates.
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.notices notices LEFT JOIN FETCH p.relationSource relation");

        List resu = q.list();
        sessionFactory.getCurrentSession().clear();

        return resu;
    }
@Transactional
    @Override
    public Person getEntity(Long id) {
//        return super.getEntity(id); //To change body of generated methods, choose Tools | Templates.
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.notices notices LEFT JOIN FETCH p.relationSource relation WHERE p.id=:id");
        q.setParameter("id", id);

//                + " LEFT JOIN FETCH s.relationWorkSource rws "
//                + "LEFT JOIN FETCH s.relationPerson rpers LEFT "
//                + "JOIN FETCH s.bibliographicType btype "
//                + "WHERE s.id=:id");
//        q.setParameter("id", id);
        
        Person resu = (Person) q.uniqueResult();
        sessionFactory.getCurrentSession().clear();
        return resu;
    }

}
