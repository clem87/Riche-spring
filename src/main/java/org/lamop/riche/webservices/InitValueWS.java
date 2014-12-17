/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.BibliographicType;
import org.lamop.riche.model.Origin;
import org.lamop.riche.model.Theme;
import org.lamop.riche.services.BibliographicTypeServiceIfs;
import org.lamop.riche.services.OrigineServiceIfs;
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
@RequestMapping("/rest/initvalue")
public class InitValueWS {

    @Autowired
    ThemeServiceIfs serviceTheme;

    @Autowired
    BibliographicTypeServiceIfs serviceBiblio;

    @Autowired
    OrigineServiceIfs origineService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/addvalue")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public void createALL() {

        Theme theme = new Theme();
        theme.setLabel("courtoisie");

        Theme t2 = new Theme();
        t2.setLabel("clercs et novices");

        Theme t3 = new Theme();
        t3.setLabel("conseils aux femmes");

        serviceTheme.addEntity(theme);
        serviceTheme.addEntity(t3);
        serviceTheme.addEntity(t2);

        //------
        BibliographicType bt = new BibliographicType();
        bt.setLabel("Monographie");

        BibliographicType bt2 = new BibliographicType();
        bt2.setLabel("Article");
        serviceBiblio.addEntity(bt);
        serviceBiblio.addEntity(bt2);

        // Origine
        Origin o = new Origin();
        o.setLabel("Couvent des Célestins");
        
        Origin o2 = new Origin();
        o2.setLabel("Saint-Victor");
        
        origineService.addEntity(o);
        origineService.addEntity(o2);

    }

}
