/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * N'est plus utilisé. Se tourner vers les outils springs
 * @author clril
 */
@Deprecated
public class PropertiesTools {
 
        private static final Logger LOG = LoggerFactory.getLogger(PropertiesTools.class);
    
    /***
     * Charge la propriété properties du fichier propFile
     * @param propFile
     * @param propertie
     * @return
     * @throws IOException 
     */
    public static String loadProperties(String propFile, String propertie) throws IOException{
        InputStream in = null;
        try {
        in = PropertiesTools.class.getClassLoader().getResourceAsStream(propFile);
        Properties configProp = new Properties();
        configProp.load(in);
        return configProp.getProperty(propertie);
        }
        finally{
            if(in != null){
                try {
                    in.close();
                } catch (Exception e) {
                    LOG.error("Error on closing InputStream ?! ", e);
                }
            }
        }
    }
}
