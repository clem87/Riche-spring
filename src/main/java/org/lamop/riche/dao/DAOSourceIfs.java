/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;

/**
 *
 * @author clril
 */
public interface DAOSourceIfs extends DAOGenericIFS<Source>{

    public void addRelation(RelationWorkSource get);
    
    public List<Source> getAllSourcesForAuthor(Person p);
    
    
}
