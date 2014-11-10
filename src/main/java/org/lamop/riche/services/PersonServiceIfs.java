/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.model.Person;

/**
 *
 * @author clril
 */
public interface PersonServiceIfs extends ServiceCRUDIfs<Person>{
    
    
    List<Person> find(String arg);
}
