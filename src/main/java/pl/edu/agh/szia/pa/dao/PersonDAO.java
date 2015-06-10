/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.criminals.Aquaintance;
import pl.edu.agh.szia.pa.model.criminals.Person;

/**
 *
 * @author Dariusz Hudziak
 */
public class PersonDAO {
    private SessionFactory factory;
    private CommonDAO commonDAO;

    public PersonDAO(CommonDAO dao) {
        this.commonDAO = dao;
        this.factory = dao.getFactory();
    }
    
    public List<Aquaintance> listAquaintance(Person p){
        
        Session s = factory.openSession();
        s.beginTransaction();
        
        List<Aquaintance> l = s.createCriteria(Aquaintance.class)
                               .add(Restrictions.eq("from", p))
                               .list();
        
        s.getTransaction().commit();
        s.close();
        
        return l;
    }
    
    public Aquaintance saveAquaintance(Person from,Person to,double level) {
        Aquaintance a = new Aquaintance(from, to);
        a.setLevel(level);
        
        Session s = factory.openSession();
        s.beginTransaction();
        
        s.persist(a);
        
        s.getTransaction().commit();
        s.close();
        return a;
    }
    
    public List<Person> listPersons(){
        Session s = factory.openSession();
        s.beginTransaction();
        List<Person> l = s.createCriteria(Person.class).list();
        s.getTransaction().commit();
        s.close();
        return l;
    }
    
    public void storePerson(Person p) throws Exception {
        Session s = factory.openSession();
        
        Address a = p.getAddress();
        if(a.getID() <= 0) {
            a = commonDAO.findAddress(a.getTown().getName(), a.getStreet(), a.getHouse());
            if(a==null) {
                commonDAO.storeAddress(p.getAddress());
            }
        }
        
        Transaction t = s.getTransaction();
        t.begin();
        
        s.persist(p);
        t.commit();
        s.close();
        
    }
    
}
