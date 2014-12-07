/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.WorkEntity;

/**
 *
 * @author clril
 */
public class DAORelationWorkSourceImpl extends DAOGenericImpl<RelationWorkSource>implements DAORelationWorkSourceIfs{

    @Override
    public List<RelationWorkSource> findRelationForSource(WorkEntity w) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RelationWorkSource.class);
        criteria.add(Restrictions.eq("workEntity", w));
        return criteria.list();
    }


    
}
