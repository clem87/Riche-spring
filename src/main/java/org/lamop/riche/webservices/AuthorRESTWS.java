/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.services.PersonServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author clril
 */
@RestController
@RequestMapping("/rest/author")
public class AuthorRESTWS {
    
    
    @Autowired
    PersonServiceIfs servicePerson;
            
    
    @GET
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/find")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Person> queryAuthor(@RequestParam("userselection") String arg){
         List<Person> l = servicePerson.find(arg);
        return l;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/testcreate")
    public void testcreate(){
        System.out.println("OUI");
        Person wTest = new Person();
        wTest.setLabel("John Steinbeck");
        
        Person p2 = new Person();
        p2.setLabel("William Faulkner");
        servicePerson.addEntity(wTest);
        servicePerson.addEntity(p2);
    }
    
}
