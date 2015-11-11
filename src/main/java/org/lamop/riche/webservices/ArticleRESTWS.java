/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lamop.riche.model.Article;
import org.lamop.riche.services.ArticleServiceIfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author clril
 */
@RestController
@RequestMapping("/rest/article")
public class ArticleRESTWS {
    
    @Autowired
    ArticleServiceIfs serviceArticle;
    
    
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/get")
    public @ResponseBody
    Article get(@PathParam("id") int id) {
        return serviceArticle.getEntity(new Long(id));
    }
    
    
        @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/post")
    public @ResponseBody
    Article update(@PathParam("id") int id, @RequestBody Article article) {
        serviceArticle.modifyEntity(article);
        return article;
    }
    
    
     @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/getall")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<Article> getAll() {
        return serviceArticle.getAll();
    }
    
}
