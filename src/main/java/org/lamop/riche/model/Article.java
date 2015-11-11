/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Article affiché sur le site. Cette entité est utilisée pour stocker le texte
 * de présentation de la page d'accueil
 *
 * @author clril
 */
@Entity
public class Article implements Serializable {

    public Article() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected String title;

    @Column(name = "content", columnDefinition = "TEXT")
    protected String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
