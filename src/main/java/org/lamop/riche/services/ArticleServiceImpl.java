/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.dao.DAOArticleIfs;
import org.lamop.riche.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class ArticleServiceImpl  implements ArticleServiceIfs{
    
    @Autowired
    DAOArticleIfs dao;
    

    @Override
    @Transactional
    public List<Article> getAll() {
        return dao.getAllEntities();
    }

    @Override
    @Transactional
    public void addEntity(Article entity) {
        dao.addEntity(entity);

    }

    @Override
    @Transactional
    public void removeEntity(Article entity) {
        dao.removeEntity(entity);
    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        Article a = dao.getEntity(id);
        if(a!=null){
            dao.removeEntity(a);
        }
    }

    @Override
    @Transactional
    public void modifyEntity(Article entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public Article getEntity(Long id) {
        return dao.getEntity(id);
    }

    public DAOArticleIfs getDao() {
        return dao;
    }

    public void setDao(DAOArticleIfs dao) {
        this.dao = dao;
    }
    
}
