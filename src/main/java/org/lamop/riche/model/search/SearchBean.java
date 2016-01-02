/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author clril
 */
public class SearchBean {

    List<SearchCriteria> searchCriteria = new ArrayList<>();

    public List<SearchCriteria> getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(List<SearchCriteria> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<SearchCriteria> returnCritheriaForField(String field) {

        List<SearchCriteria> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(field)) {

            for (Iterator<SearchCriteria> iterator = searchCriteria.iterator(); iterator.hasNext();) {
                SearchCriteria crit = iterator.next();

                if (field.equals(crit.getField())) {
                    list.add(crit);
                }
            }
        }
        return list;
    }

}
