/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Origin;
import org.lamop.riche.model.Theme;
import org.lamop.riche.services.OrigineServiceIfs;
import org.lamop.riche.services.OrigineServiceImpl;
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
@RequestMapping("/rest/origine")
public class OrigineRESTWS {
    
        @Autowired
        OrigineServiceIfs serviceOrigine;
        
        
            /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<Origin> getAll() {

        return serviceOrigine.getAll();
//        return null;
    }
    
        @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/addtest")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody

    public void addTestValue() {
            Origin o1 = new Origin();
            Origin o2 = new Origin();
            Origin o3 = new Origin();
            
            o1.setLabel("Origine label1");
            o2.setLabel("Origine label2");
            o3.setLabel("Origine label3");
            
            serviceOrigine.addEntity(o1);
            serviceOrigine.addEntity(o2);
            serviceOrigine.addEntity(o3);
    }
    
}
