/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Yerlin Leal
 */
public class Person {

    private String id;
    private String firstName;
    private String surname1;
    private String surname2;
    private String birthdate;
    private String country;
    private String fatherId;

    public Person() {
    }

    public Person(String id, String firstName, String surname1, String surname2, String birthdate, String country, String fatherId) {
        this.id = id;
        this.firstName = firstName;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.birthdate = birthdate;
        this.country = country;
        this.fatherId = fatherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", surname1=" + surname1 + ", surname2=" + surname2 + ", birthdate=" + birthdate + ", country=" + country + ", fatherId=" + fatherId + '}';
    }

} // end class
