/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.model.search.SearchBean;

/**
 *
 * @author clril
 */
public interface DAOWorkIFS extends DAOGenericIFS<WorkEntity>{
    
    public void testSpeWork(WorkEntity w);

    public List<WorkEntity> getWorkForAuthor(WorkAuthor author);

    public List<WorkEntity> getWorkForSource(Source s);

    /***
     * retourne le compte de toutes les sources
     * @return 
     */
    public Long getAllEntitiesCount();

    /***
     * Recherche basé sur criteria en utilisant les données du bean envoyé en argument
     * @param search
     * @return 
     */
    public List<WorkEntity> search(SearchBean search);

}
