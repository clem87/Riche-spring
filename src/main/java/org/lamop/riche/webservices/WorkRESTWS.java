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
import org.lamop.riche.model.RelationWorkSourceDTO;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntityDTO;
import org.lamop.riche.model.search.SearchBean;
import org.lamop.riche.model.search.SearchCriteria;

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
    List<WorkEntityDTO> getAllWork() {
        List<WorkEntityDTO> list = serviceWork.getAllDTO();
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getallcount")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    Long getAllWorkCount() {
        Long nbr = serviceWork.getAllCount();
        return nbr;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getWorkForAuthor")
    @Produces(MediaType.APPLICATION_JSON)
    List<WorkEntity> getWorkForAuthor(@PathParam("authorId") Long authorId) {
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
//        WorkEntity work = new WorkEntity();
        serviceWork.addEntity(work);
        return work;
    }

    /**
     * *
     * Récupérer une source
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/get")
    public @ResponseBody
    WorkEntity get(@PathParam("id") int id) {
        return serviceWork.getEntity(new Long(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/testcreate")
    public void testcreate() {
        System.out.println("OUI");
        WorkEntity wTest = new WorkEntity();
        wTest.setTitle("Le livre de test");

        WorkAuthor p = new WorkAuthor();
        p.setLabel("auteur de test");

        wTest.getAuthors().add(p);
        serviceWork.addEntity(wTest);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/post")
    public @ResponseBody
    WorkEntity update(@PathParam("id") int id, @RequestBody WorkEntity work) {
        serviceWork.modifyEntity(work);
        return work;
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/search")
    public @ResponseBody
    List<WorkEntityDTO> search(@RequestBody SearchBean search) {

        return serviceWork.search(search);

    }

//        @RequestMapping(method = RequestMethod.GET, value = "/testSearch")w
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/testsearch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkEntityDTO> testSearch() {

        SearchBean searchBean = new SearchBean();

//            SearchCriteria criteria = new SearchCriteria();
//            criteria.setField("centuryMax");
//            criteria.setValue("10");
//            
//            SearchCriteria criteria2 = new SearchCriteria();
//            criteria2.setField("centuryMin");
//            criteria2.setValue("8");            
//            searchBean.getSearchCriteria().add(criteria);
//            searchBean.getSearchCriteria().add(criteria2);
//                    SearchCriteria criteria = new SearchCriteria();
//                    criteria.setField("theme");
//                    criteria.setValue("1");
//                    searchBean.getSearchCriteria().add(criteria);
        SearchCriteria criteria = new SearchCriteria();
        criteria.setField("authors");
        criteria.setValue("3");
        searchBean.getSearchCriteria().add(criteria);

        List<WorkEntityDTO> resu = serviceWork.search(searchBean);
        return resu;
    }
}
