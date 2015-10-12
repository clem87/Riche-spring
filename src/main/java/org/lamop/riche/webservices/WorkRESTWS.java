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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.WorkAuthor;

/**
 *
 * @author clril
 */
@RestController
@RequestMapping("/rest/work")
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
    
     @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getWorkForAuthor")
    @Produces(MediaType.APPLICATION_JSON)
    List<WorkEntity> getWorkForAuthor(@PathParam("authorId") Long authorId){
        List<WorkEntity> list = serviceWork.getWorkForAuthor(authorId);
        return list;
    }
    

    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void remove(@PathParam("id") int id) {
        serviceWork.removeEntity(new Long(id));
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON, value = "/put")
    public @ResponseBody
    WorkEntity create(@RequestBody WorkEntity work) {
        serviceWork.addEntity(work);
        return work;
    }
    
    /***
     * Récupérer une source
     * @param id
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET, headers ="Accept=application/json" , value = "/get")
    public @ResponseBody WorkEntity get(@PathParam("id") int id){
        return serviceWork.getEntity(new Long(id));
    } 
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/testcreate")
    public void testcreate(){
        System.out.println("OUI");
        WorkEntity wTest = new WorkEntity();
        wTest.setTitle("Le livre de test");
        
        WorkAuthor p = new WorkAuthor();
        p.setLabel("auteur de test");
        
        wTest.getAuthors().add(p);
        serviceWork.addEntity(wTest);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/post")
    public @ResponseBody WorkEntity update(@PathParam("id") int id, @RequestBody WorkEntity work){
        serviceWork.modifyEntity(work);
        return work;
    }
}
