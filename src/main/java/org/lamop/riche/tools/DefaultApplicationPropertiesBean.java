/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.tools;

import java.util.Properties;
import org.apache.commons.lang.StringUtils;

/**
 * Permet de charger le application.properties par défault. Si aucune info n'a 
 * été spécifié dans le system .properties ce sera classpath:application.properties.
 * 
 * Pour externaliser application.properties ajouté 
 * -Driche.application.properties=file:/etc/richeApp/application.properties aux parametres du tomcat
 * @author clril
 */
public class DefaultApplicationPropertiesBean {
    
    String propertie;
    

    public String getPropertie() {
        if(propertie == null){
            final Properties systemProperties = System.getProperties();
            String val = systemProperties.getProperty("riche.application.properties");
            if(StringUtils.isNotEmpty(val)){
                propertie = val;
            }
            else{
                propertie = "classpath:application.properties";                
            }
        }
        return propertie;
    }
}
