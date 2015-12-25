/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.BibliographicType;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationSourcePerson;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.Theme;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.services.BibliographicTypeServiceIfs;
import org.lamop.riche.services.ImportServiceIfs;
import org.lamop.riche.services.PersonServiceIfs;
import org.lamop.riche.services.SourceServiceIfs;
import org.lamop.riche.services.ThemeServiceIfs;
import org.lamop.riche.services.WorkServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    WorkServiceIfs workService;

    @Autowired
    SourceServiceIfs sourceService;

    @Autowired
    PersonServiceIfs personService;
    
    @Autowired
    ImportServiceIfs importService;
    
    
    
     @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/importall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public void importall() {
        importService.importBDD();
        
    }
    
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/addTheme")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public void addTheme() {
        importService.initThemes();
    }
  

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

        //Work
        WorkEntity w1 = new WorkEntity();
        w1.setTitle("Doctrina Osii de observatione disciplinae dominicae");
        w1.setNote("48 préceptes; sentences morales élémentaires que l'enfant apprend par coeur");
        w1.setCenturyMax(11);
        w1.setCenturyMin(5);

        Source s1 = new Source();
        s1.setTitle("Haut Moyen Âge, culture, éducation et société");
        s1.setReleaseYear(1990);
        Person p1 = new Person();
        p1.setLabel("Sot, Michel");
        personService.addEntity(p1);

        sourceService.addEntity(s1);

        RelationWorkSource r1 = new RelationWorkSource();
        r1.setSource(s1);
        r1.setWorkEntity(w1);
        w1.getRelationWorkSource().add(r1);
        s1.getRelationWorkSource().add(r1);
        r1.setExtract("p.p. 188-190");

        workService.addEntity(w1);

        ///-----------
        WorkEntity w2 = new WorkEntity();
        w2.setTitle("Lettre à son disciple Donatus");
        w2.setCenturyMin(5);
        w2.setCenturyMax(11);

        Person p2 = new Person();
        p2.setLabel("Fulgence de Ruspe");
        personService.addEntity(p2);

        Source s2 = new Source();
        s2.setTitle("Patrologia Latina");
        sourceService.addEntity(s2);

        RelationWorkSource r2 = new RelationWorkSource();
        r2.setWorkEntity(w2);
        r2.setSource(s2);
        w2.getRelationWorkSource().add(r2);
        workService.addEntity(w2);

        //----
        WorkEntity w3 = new WorkEntity();
        w3.setTitle("Puerorum speculum ");
        w3.setCenturyMax(10);
        w3.setCenturyMin(10);
        Person isamar = new Person();
        isamar.setLabel("Isambard de Fleury");
        
 

      
        
        Person auteur1 = new Person();
        auteur1.setLabel("Vidier, Alexandre");
        personService.addEntity(auteur1);
        
        Person auteur2 =new Person();
        auteur2.setLabel("Abbaye Saint-Benoît de Fleur");
                personService.addEntity(auteur2);
                
                       Source s3 = new Source();
        s3.setTitle("historiographie à Saint-Benoît-sur-Loire et les miracles de saint Benoît");
        s3.setReleaseTown("Paris");
        s3.setReleaseYear(1965);
        
        RelationSourcePerson relationSourcePerson = new RelationSourcePerson();
        relationSourcePerson.setSource(s3);
        relationSourcePerson.setPerson(p2);
        s3.addRelationPerson(relationSourcePerson);
        
        
        sourceService.addEntity(s3);
        
          RelationWorkSource r3 = new RelationWorkSource();
        r3.setExtract("p.97");
        r3.setSource(s3);
        r3.setWorkEntity(w3);
        w3.addRelationWorkSource(r3);
        
        workService.addEntity(w3);
                
    }
    
    
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/test")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public void test() {
        System.out.println("coucou");
    }
}
