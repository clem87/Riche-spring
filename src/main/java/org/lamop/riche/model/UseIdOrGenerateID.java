/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.model;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.SequenceGenerator;
/**
 *  Permet de génerer la clé primaire des Source. Ne pas autogénérer la clé si elle est déjà existante
 * @author clril
 */
public class UseIdOrGenerateID extends IdentityGenerator{
    
@Override
public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
    if (obj == null) throw new HibernateException(new NullPointerException()) ;

    if ((((Source) obj).getId()) == null) {
        Serializable id = super.generate(session, obj) ;
        return id;
    } else {
        return ((Source) obj).getId();
    }
}

}
