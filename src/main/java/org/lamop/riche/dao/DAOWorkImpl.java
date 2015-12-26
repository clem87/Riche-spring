/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
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
    public List<WorkEntity> search(SearchBean search) {

        Set<SearchCriteria> criteres = search.getSearchCriteria();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkEntity.class);

        List<SearchCriteria> dateCriteria = new ArrayList<>();
        dateCriteria = search.returnCritheriaForField("centuryMax");
        dateCriteria.addAll(search.returnCritheriaForField("centuryMin"));

        gestionDateCriteria(dateCriteria, criteria);
        gestionCriteriaWithJoin(search, criteria, "theme");
        gestionCriteriaWithJoin(search, criteria, "authors");

        return criteria.list();
    }

    public void gestionCriteriaWithJoin(SearchBean search, Criteria criteria, String field) {
        List<SearchCriteria> themeCriteria = search.returnCritheriaForField(field);

        if (!themeCriteria.isEmpty()) {
            Criteria joinCriteria = criteria.createCriteria(field, JoinType.INNER_JOIN);
            for (int i = 0; i < themeCriteria.size(); i++) {
                SearchCriteria themeCrit = themeCriteria.get(i);
                joinCriteria.add(Restrictions.eq("id", Long.valueOf(themeCrit.getValue())));
            }
        }
    }

    /**
     * *
     * Gere les critères de date envoyé dans la liste dateCriteria pour ajouter
     * les bon criteria hibernate dans criteria
     *
     * @param dateCriteria
     * @param criteria
     * @throws NumberFormatException
     */
    private void gestionDateCriteria(List<SearchCriteria> dateCriteria, Criteria criteria) throws NumberFormatException {
        if (!dateCriteria.isEmpty() && dateCriteria.size() == 2) {
            int min = -999;
            int max = -999;
            for (int i = 0; i < dateCriteria.size(); i++) {
                SearchCriteria critere = dateCriteria.get(i);
                if (critere.getField().equals("centuryMax")) {
                    max = Integer.valueOf(critere.getValue());
                } else {
                    min = Integer.valueOf(critere.getValue());
                }
            }
            List<LogicalExpression> dateLogicalExpression = new ArrayList<>();
            for (int i = 0; i < dateCriteria.size(); i++) {
                SearchCriteria critere = dateCriteria.get(i);

                SimpleExpression exprMin = Restrictions.le(critere.getField(), max);
                SimpleExpression exprMax = Restrictions.ge(critere.getField(), min);

                dateLogicalExpression.add(Restrictions.and(exprMin, exprMax));
            }
            criteria.add(Restrictions.or(dateLogicalExpression.get(0), dateLogicalExpression.get(1)));
        } else if (!dateCriteria.isEmpty() && dateCriteria.size() == 1) {
            SearchCriteria critere = dateCriteria.get(0);
            Integer val = null;

            try {
                val = Integer.valueOf(critere.getValue());
            } catch (Exception e) {
                log.info("impossible de parser la date envoyée par le client", e);
            }

            if (val != null) {
                if ("centuryMax".equals(critere.getField())) {
                    SimpleExpression exprMax = Restrictions.le("centuryMax", val);
                    SimpleExpression exprMin = Restrictions.le("centuryMin", val);
                    criteria.add(Restrictions.or(exprMin, exprMax));
                } else if ("centuryMin".equals(critere.getField())) {
                    SimpleExpression exprMax = Restrictions.ge("centuryMax", val);
                    SimpleExpression exprMin = Restrictions.ge("centuryMin", val);
                    criteria.add(Restrictions.or(exprMin, exprMax));
                }
            }
        }

    }
}
