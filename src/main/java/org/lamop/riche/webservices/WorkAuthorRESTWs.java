/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.services.WorkAuthorServiceIfs;
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
@RequestMapping("/rest/workauthor")
public class WorkAuthorRESTWs {
    
    @Autowired
    WorkAuthorServiceIfs workAuthorService;
    
    
        @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<WorkAuthor> getAll() {
        return workAuthorService.getAll();
    }
    
     @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.DELETE)
    public void remove(@PathParam("id") int id) {
        workAuthorService.removeEntity(new Long(id));
    }

    @POST
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON, value = "/add")
    public @ResponseBody
    WorkAuthor create(@RequestBody WorkAuthor work) {
        System.out.println("Create new WorkAuthor : " + work);
        workAuthorService.addEntity(work);
        return work;
    }
    
    
       @RequestMapping(method = RequestMethod.GET, headers ="Accept=application/json" )
    public @ResponseBody WorkAuthor get(@PathParam("id") int id){
        System.out.println("GET ID " + id);
        return workAuthorService.getEntity(new Long(id));
    } 
    
    
        @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public @ResponseBody WorkAuthor update(@PathParam("id") int id, @RequestBody WorkAuthor work){
        System.out.println("ID " + id);
        System.out.println("Modify " + work );
        workAuthorService.modifyEntity(work);
        return work;
    }
    
    
        @GET
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/find")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<WorkAuthor> queryAuthor(@RequestParam("userselection") String arg){
         List<WorkAuthor> l = workAuthorService.find(arg, true);
        return l;
    }
}
