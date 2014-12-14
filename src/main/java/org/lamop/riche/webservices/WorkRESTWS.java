/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.services.WorkServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Person;

/**
 *
 * @author clril
 */
@RestController
@RequestMapping("/rest/test")
//@EnableAutoConfiguration
public class WorkRESTWS {

    @Autowired
    WorkServiceIfs serviceWork;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<WorkEntity> getAllWork() {
        
        List<WorkEntity> list = serviceWork.getAll();
        
        return list;

    }

//    @RequestMapping(method = RequestMethod.POST, value = "/add")
//    public void add(@RequestBody WorkEntity work) {
//        System.out.println("ADD work " + work);
//        serviceWork.addEntity(work);
//    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.DELETE)
    public void remove(@PathParam("id") int id) {
        serviceWork.removeEntity(new Long(id));
    }

    @POST
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON, value = "/add")
    public @ResponseBody
    WorkEntity create(@RequestBody WorkEntity work) {
        System.out.println("Create new work : " + work);
        System.out.println("work title : " + work.getTitle());
        System.out.println("Work author size " + work.getAuthors().size());

                
        serviceWork.addEntity(work);
        return work;
    }
    
    @RequestMapping(method = RequestMethod.GET, headers ="Accept=application/json" )
    public @ResponseBody WorkEntity get(@PathParam("id") int id){
        System.out.println("GET ID " + id);
        return serviceWork.getEntity(new Long(id));
    } 
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/testcreate")
    public void testcreate(){
        System.out.println("OUI");
        WorkEntity wTest = new WorkEntity();
        wTest.setTitle("Le livre de test");
        
        Person p = new Person();
        p.setLabel("auteur de test");
        
        wTest.getAuthors().add(p);
        serviceWork.addEntity(wTest);
    }

    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public @ResponseBody WorkEntity update(@PathParam("id") int id, @RequestBody WorkEntity work){
        System.out.println("ID " + id);
        System.out.println("Modify " + work );
        serviceWork.modifyEntity(work);
        return work;
    }
    

}
