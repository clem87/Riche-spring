/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.WorkAuthor;

/**
 *
 * @author clril
 */
public interface DAOWorkAuthorIfs extends DAOGenericIFS<WorkAuthor>{

//    public List<WorkAuthor> findByExactLabel(String label, boolean approx);
    
    public List<WorkAuthor> find(String arg, boolean approx);
    
}
