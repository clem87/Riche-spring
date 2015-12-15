/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.model.Source;

/**
 *
 * @author clril
 */
public interface SourceServiceIfs extends ServiceCRUDIfs<Source>{
    
    List<Source> find(String arg);

    public Long getAllCount();
    
   
    
}
