/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.dao;

import java.util.List;
import org.lamop.riche.model.Person;

/**
 *
 * @author clril
 */
public interface DAOPersonIfs extends DAOGenericIFS<Person>{
    public List<Person> find(String arg);
}
