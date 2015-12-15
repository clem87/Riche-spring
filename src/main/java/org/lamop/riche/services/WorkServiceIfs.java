/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.util.List;
import org.lamop.riche.model.WorkEntity;
import org.lamop.riche.model.WorkEntityDTO;
import org.lamop.riche.model.search.SearchBean;

/**
 *
 * @author clril
 */
public interface WorkServiceIfs extends ServiceCRUDIfs<WorkEntity>{

    public List<WorkEntity> getWorkForAuthor(long id);

    public Long getAllCount();

    public List<WorkEntityDTO> search(SearchBean search);

}
