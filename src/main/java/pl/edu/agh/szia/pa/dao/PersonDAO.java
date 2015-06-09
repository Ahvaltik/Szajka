/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import pl.edu.agh.szia.pa.model.criminals.Person;

/**
 *
 * @author Dariusz Hudziak
 */
public class PersonDAO {
    private SessionFactory factory;
//    private AddressDAO addressDAO;

//    public PersonDAO(AddressDAO dao) {
//        this.addressDAO = dao;
//        this.factory = dao.getFactory();
//    }
    
    public void storePerson(Person c) {
        Session s = factory.openSession();
        
//        Address a = c.getLocation();
//        if(a.getID() <= 0) {
//            a = addressDAO.findAddress(a.getTown().getName(), a.getStreet(), a.getHouse());
//            if(a==null) {
//                addressDAO.storeAddress(c.getLocation());
//            }
//        }
        
        Transaction t = s.getTransaction();
        t.begin();
        
        s.persist(c);
        t.commit();
        s.close();
        
    }
    
}
