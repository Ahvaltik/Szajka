/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.edu.agh.szia.pa.model.address.Address;
import pl.edu.agh.szia.pa.model.address.Town;

/**
 *
 * @author uriel
 */
public class AddressDAO {
    
    private SessionFactory factory;

    public AddressDAO(SessionFactory factory) {
        this.factory = factory;
    }
    
    public void storeAddress(Address a){
        
        Session s = factory.getCurrentSession();
        Transaction t = s.getTransaction();
        t.begin();
        
        List<Town> towns = s.createCriteria(Town.class)
                            .add(Restrictions.eq("name", a.getTown().getName()))
                            .list();
        if(towns.size() < 1) {
            s.persist(a.getTown());
        }
        
        s.persist(a);
    }
    
}
