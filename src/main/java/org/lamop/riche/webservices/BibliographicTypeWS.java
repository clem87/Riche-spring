/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.BibliographicType;
import org.lamop.riche.services.BibliographicTypeServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author clril
 */
@RestController
@RequestMapping("/rest/bibliographictype")
public class BibliographicTypeWS {
    
    @Autowired
    BibliographicTypeServiceIfs service;

    /**
     * *
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<BibliographicType> getAll() {
        
        return service.getAll();
//        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/addtest")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    
    public void addTestValue() {
        
        BibliographicType bt = new BibliographicType();
        bt.setLabel("Monographie");
        
        BibliographicType bt2 = new BibliographicType();
        bt2.setLabel("Article");
        service.addEntity(bt);
    }
    
}
