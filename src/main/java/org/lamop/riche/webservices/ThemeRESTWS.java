/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.swing.text.View;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Theme;
import org.lamop.riche.services.ThemeServiceIfs;
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
@RequestMapping("/rest/theme")
public class ThemeRESTWS {

    @Autowired
    ThemeServiceIfs service;

    /**
     *
     * @return
     */
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<Theme> getAll() {

        return service.getAll();
//        return null;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/addtest")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody

    public void addTestValue() {

        
        Theme theme = new Theme();
        theme.setLabel("courtoisie");
        
        Theme t2 = new Theme();
        t2.setLabel("clercs et novices");
        
        Theme t3 = new Theme();
        t3.setLabel("conseils aux femmes");
        
        service.addEntity(theme);
        service.addEntity(t3);
        service.addEntity(t2);
        
    }

}
