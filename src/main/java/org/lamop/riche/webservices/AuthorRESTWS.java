/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Person;
import org.lamop.riche.services.PersonServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<Person> getAll() {

        return servicePerson.getAll();
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON, value = "/put")
    public @ResponseBody
    Person create(@RequestBody Person person) {
        servicePerson.addEntity(person);
        return person;
    }
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Person get(@PathParam("id") int id) {
        return servicePerson.getEntity(new Long(id));
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/post")
    public @ResponseBody
    Person update(@PathParam("id") int id, @RequestBody Person work) {
        servicePerson.modifyEntity(work);
        return work;
    }
    
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void remove(@PathParam("id") int id) {
        servicePerson.removeEntity(new Long(id));
    }
}
