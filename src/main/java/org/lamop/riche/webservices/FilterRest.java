/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.webservices;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.lamop.riche.tools.PropertiesTools;
import org.springframework.beans.factory.annotation.Value;

/**
 * Filter used for all restService. Add Access-Control-Allow-Origin for test
 *
 * @author clril
 */
public class FilterRest implements Filter {

    List<String> listIpOriginAllowed = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            addHeaderAllowOrigin(request, response);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Request-Headers", "X-Requested-With, accept, content-type");
            response.setHeader("Access-Control-Allow-Headers", "Origin,  X-Requested-With, X-CSRF-Token, X-Requested-With, Content-Type, Accept");

            if (!request.getMethod().equals("OPTIONS")) {
                chain.doFilter(req, res);
            } else {
                System.out.println("LA OPTIONS");
            }
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * *
     * Ajoute le header Access-Control-Allow-Origin dans la reponse en fonction
     * du header Origin de la requeête en vérifiant la properties front.IpOrigin
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void addHeaderAllowOrigin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (listIpOriginAllowed == null) {
            String frontIpOrigin = PropertiesTools.loadProperties("appli.properties", "front.IpOrigin");
            if (StringUtils.isNotEmpty(frontIpOrigin)) {
                String[] arrayIpOriginAllowed = frontIpOrigin.split(",");
                listIpOriginAllowed = Arrays.asList(arrayIpOriginAllowed);
            }
        }
        
        String originInrequest = request.getHeader("Origin");
        if (listIpOriginAllowed.contains(originInrequest)) {
            response.setHeader("Access-Control-Allow-Origin", originInrequest);
        }
    }
}
