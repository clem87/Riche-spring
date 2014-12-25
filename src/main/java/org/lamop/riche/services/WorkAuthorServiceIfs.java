/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.model.WorkAuthor;

/**
 *
 * @author clril
 */
public interface WorkAuthorServiceIfs extends ServiceCRUDIfs<WorkAuthor>{

//    public List<WorkAuthor> find(String arg);
        public List<WorkAuthor> find(String arg, boolean approx) ;
    
}
