/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.common.Town;

/**
 *
 * @author uriel
 */
public class AddressDAO {
    
    private SessionFactory factory;

    public AddressDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }
    
    public Address findAddress(String town,String street,String house) {
        Session s = factory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Map<String,String> crit = new HashMap<String, String>();
        crit.put("street",street);
        crit.put("house",house);
        
        List<Address> l =  s.createCriteria(Address.class,"a")
                           .add(Restrictions.allEq(crit))
                           .createAlias("a.town", "t")
                           .add(Restrictions.eq("t.name", town))
                           .list();
        t.commit();
        s.close();
        
        if(l.size() > 0){
            return l.get(0);
        } else return null;
    }
    
    public void storeAddress(Address a){
        
        Session s = factory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        
        List<Town> towns = s.createCriteria(Town.class)
                            .add(Restrictions.eq("name", a.getTown().getName()))
                            .list();
        if(towns.size() < 1) {
            s.persist(a.getTown());
        } else {
            a.setTown(towns.get(0));
        }
        
        s.persist(a);
        s.close();
    }
    
}
