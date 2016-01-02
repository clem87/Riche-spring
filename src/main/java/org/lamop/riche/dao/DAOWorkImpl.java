/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.Predicate;
import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.jpa.criteria.expression.SimpleCaseExpression;
import org.hibernate.sql.JoinType;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.model.search.SearchBean;
import org.lamop.riche.model.search.SearchCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
@Repository
public class DAOWorkImpl extends DAOGenericImpl<WorkEntity> implements DAOWorkIFS {

    @Override
    public void testSpeWork(WorkEntity w) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public List<WorkEntity> getAllEntities() {

        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w "
                + "LEFT JOIN FETCH w.authors authors "
                + "LEFT JOIN FETCH w.relationWorkSource relation "
                + "LEFT JOIN FETCH w.theme theme");

        List<WorkEntity> returnList = q.list();
        sessionFactory.getCurrentSession().clear();
        return returnList;
    }

    @Override
    public WorkEntity getEntity(Long id) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w "
                + "LEFT JOIN FETCH w.authors authors "
                //                + "LEFT JOIN FETCH w.origin origin "
                + "LEFT JOIN FETCH w.relationWorkSource relation "
                + "LEFT JOIN FETCH w.theme theme "
                + "WHERE w.id=:id");
        q.setParameter("id", id);
        WorkEntity result = (WorkEntity) q.uniqueResult();
        sessionFactory.getCurrentSession().clear();
        return result;
    }

    /**
     * *
     * get all work for the author sended in param
     *
     * @param author author used in where clause
     * @return list of work
     */
    @Override
    public List<WorkEntity> getWorkForAuthor(WorkAuthor author) {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT w FROM WorkEntity w JOIN w.authors au WHERE au.id = :id");
        q.setParameter("id", author.getId());
        List<WorkEntity> result = q.list();
        return result;
    }

    @Override
    public List<WorkEntity> getWorkForSource(Source s) {

        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT w FROM WorkEntity w JOIN w.relationWorkSource rel ON rel.workEntity = w.id JOIN rel.source s WHERE s.id =:sourceId");
        q.setParameter("sourceId", s.getId());
        List<WorkEntity> result = q.list();
        return result;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getAllEntitiesCount() {
        org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM WorkEntity");

        Long result = (Long) q.uniqueResult();
        return result;
    }

   
    
    @Override
    public List<WorkEntity> search(SearchBean search){
        
        Criteria hibernateCriteria = sessionFactory.getCurrentSession().createCriteria(WorkEntity.class);
        
        List<SearchCriteria> listSearch = search.getSearchCriteria();
        
        //--- On groupe les critères de recherche en sous groupes en fonction des AND
        List<List<SearchCriteria>> andListGroup = new ArrayList<>();
        andListGroup.add(new ArrayList<SearchCriteria>());
        for (Iterator<SearchCriteria> iterator = listSearch.iterator(); iterator.hasNext();) {
            SearchCriteria criteria = iterator.next();
           
            if("AND".equals(criteria.getOperator())){
                List<SearchCriteria> andList = new ArrayList<>();
                andList.add(criteria);
                andListGroup.add(andList);   
            }
            else{
                andListGroup.get(andListGroup.size()-1).add(criteria);
            }
        }
        
        
        // Pour chaque And
        List<SimpleExpression> simpleExpr = new ArrayList<>();
        for (int i = 0; i < andListGroup.size(); i++) {
            List<SearchCriteria> listAnd = andListGroup.get(i);
            
            
            Criteria joinTheme = null;
            List<SimpleExpression> themeSimpleExpression = new ArrayList<>();
            
            for (int j = 0; j < listAnd.size(); j++) {
                SearchCriteria crit = listAnd.get(j);
                
                if("title".equals(crit.getField())){
                    simpleExpr.add(Restrictions.like(crit.getField(), "%"+crit.getValue()+"%"));
                }
                
                else if ("theme".equals(crit.getField())){
                    if(joinTheme==null){
                        joinTheme = hibernateCriteria.createCriteria(crit.getField(), JoinType.INNER_JOIN);                        
                    }
                    themeSimpleExpression.add(Restrictions.eq("id", Long.valueOf(crit.getValue())));
                }
                
                else if("centuryMax".equals(crit.getField())){
                    simpleExpr.add(Restrictions.le("centuryMax", Integer.valueOf(crit.getValue())));
                    simpleExpr.add(Restrictions.le("centuryMin", Integer.valueOf(crit.getValue())));
                }
                else if("centuryMin".equals(crit.getField())){
                    simpleExpr.add(Restrictions.ge("centuryMax", Integer.valueOf(crit.getValue())));
                    simpleExpr.add(Restrictions.ge("centuryMin", Integer.valueOf(crit.getValue())));
                }
            }
            
            if(joinTheme != null && !themeSimpleExpression.isEmpty()){
                joinTheme.add(Restrictions.or(themeSimpleExpression.toArray(new SimpleExpression[0])));
            }
            
        SimpleExpression[] arraySimpleExpression = simpleExpr.toArray(new SimpleExpression[0]);
        hibernateCriteria.add(Restrictions.and(arraySimpleExpression));
        }
     
        
        
        return hibernateCriteria.list();
    }
    

}
