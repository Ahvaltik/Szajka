package pl.edu.agh.szia.pa.model.person;

import pl.edu.agh.szia.pa.model.address.Address;

import javax.persistence.*;

/**
 * Created by Puszek_SE on 2015-06-09.
 */
@Entity
@Table(name="TBL_PERSON")
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String description;
    private Address location;

    public Person(){}

    public Person(String firstName,String lastName,Address location,String description){
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }


    @Id
    @Column(name = "FLD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="FLD_FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="FLD_LASTNAME")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="FLD_DESCRIPTION",
            nullable=true,
            length=200)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name="FKF_LOCATION",
            nullable = false)
    public Address getLocation() {
        return location;
    }
    public void setLocation(Address location) {
        this.location = location;
    }
}
