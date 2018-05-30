/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.PersonData;
import domain.Person;
import java.io.IOException;
import org.jdom.JDOMException;

/**
 *
 * @author Yerlin Leal
 */
public class PersonBusiness {

    private PersonData personData;

    public PersonBusiness() throws JDOMException, IOException {
        this.personData = new PersonData();
    } // constructor

    public String updatePerson(Person newData) throws IOException {
        return personData.updatePerson(newData);
    }

    public boolean deletePerson(String id) throws IOException {
        return this.personData.deletePerson(id);
    } // deletePerson

    public void insertPerson(Person person) throws IOException {
        this.personData.insertPerson(person);
    }

    public Person[] getAllPeople() {
        return this.personData.getAllPeople();
    }

} // end class
