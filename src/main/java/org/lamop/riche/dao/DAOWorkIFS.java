/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;

/**
 *
 * @author clril
 */
public interface DAOWorkIFS extends DAOGenericIFS<WorkEntity>{
    
    public void testSpeWork(WorkEntity w);

    public List<WorkEntity> getWorkForAuthor(WorkAuthor author);

}
