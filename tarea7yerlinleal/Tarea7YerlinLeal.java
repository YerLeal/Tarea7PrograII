/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7yerlinleal;

import data.PersonData;
import domain.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 *
 * @author Yerlin Leal
 */
public class Tarea7YerlinLeal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PersonData data = new PersonData();
//            data.insertPerson(new Person(id, firstName, surname1, surname2, birthdate, country, fatherId));
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Tarea7YerlinLeal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } // main

} // end class
