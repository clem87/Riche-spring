/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.WorkAuthor;

/**
 *
 * @author clril
 */
public class DAOWorkAuthorImpl extends DAOGenericImpl<WorkAuthor> implements DAOWorkAuthorIfs{

    @Override
    public List<WorkAuthor> find(String arg, boolean approx) {
         Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkAuthor.class);
         if(approx){
        criteria.add(Restrictions.like("label", "%"+arg+"%"));             
         }
         else{
        criteria.add(Restrictions.like("label", arg));             
         }

        List resu = criteria.list();
        return resu;
//        return super.find(arg); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
}
