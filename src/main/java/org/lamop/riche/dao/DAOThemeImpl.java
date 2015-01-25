/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.Theme;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class DAOThemeImpl  extends DAOGenericImpl<Theme> implements DAOThemeIfs{

    @Transactional
    @Override
    public List<Theme> find(String arg) {
                 org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT t FROM Theme t LEFT JOIN FETCH t.works w WHERE t.label LIKE '%"+arg+"%'");
       List result = q.list();
               sessionFactory.getCurrentSession().clear();
        return result;
        
//        return super.find(arg); //To change body of generated methods, choose Tools | Templates.
    }




    
    
    
    
}
