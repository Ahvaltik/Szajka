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
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.crime.CrimeReport;
import pl.edu.agh.szia.pa.model.crime.CrimeCategory;

/**
 *
 * @author Dariusz Hudziak
 */
public class CrimeDAO {
    private SessionFactory factory;
    private CommonDAO addressDAO;

    public CrimeDAO(CommonDAO dao) {
        this.addressDAO = dao;
        this.factory = dao.getFactory();
    }
    
    public void storeCrime(CrimeReport c) {
        Session s = factory.openSession();
        
        
        Address a = c.getLocation();
        if(a.getID() <= 0) {
            a = addressDAO.findAddress(a.getTown().getName(), a.getStreet(), a.getHouse());
            if(a==null) {
                addressDAO.storeAddress(c.getLocation());
            }
        }
        
        Transaction t = s.getTransaction();
        t.begin();
        
        
       
        
        s.persist(c);
        t.commit();
        s.close();
        
    }
    
    public void test(CrimeReport r) {
        
    }
    
}
