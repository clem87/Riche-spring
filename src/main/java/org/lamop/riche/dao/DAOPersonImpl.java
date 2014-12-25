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

/**
 *
 * @author clril
 */
public class DAOPersonImpl extends DAOGenericImpl<Person> implements DAOPersonIfs{
    
//    private static String REQ_FIND = ""
    
    @Override
    public List<Person> find(String arg){
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.like("label", "%"+arg+"%"));
        List resu = criteria.list();
        return resu;
       
    }

    @Override
    public List<Person> getPersonFromLabel(String s) {
             Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.like("label", s));
          List resu = criteria.list();
        return resu;
    }




    
}
