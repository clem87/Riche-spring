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
import org.lamop.riche.model.Source;
import org.lamop.riche.services.SourceServiceIfs;
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
@RequestMapping("/rest/source")
public class SourceRESTWS {

    @Autowired
    SourceServiceIfs serviceSource;
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<Source> getAll() {
        return serviceSource.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/find")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Source> queryAuthor(@RequestParam("userselection") String arg) {
        List<Source> l = serviceSource.find(arg);
        return l;
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON, value = "/put")
    public @ResponseBody
    Source create(@RequestBody Source source) {
        serviceSource.addEntity(source);
        return source;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Source get(@PathParam("id") int id) {
        return serviceSource.getEntity(new Long(id));
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/post")
    public @ResponseBody
    Source update(@PathParam("id") int id, @RequestBody Source work) {
        serviceSource.modifyEntity(work);
        return work;
    }

    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void remove(@PathParam("id") int id) {
        serviceSource.removeEntity(new Long(id));
    }
}
