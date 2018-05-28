/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7yerlinleal;

import data.PersonData;
import domain.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
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
            data.insertPerson1(new Person("111213", "PEPE", "Lopéz", "Caza", "14/06/97", "Costa Rica", "56789"));
//            data.insertPerson1(new Person("56782", "PEPE", "Lopéz", "Caza", "14/06/97", "Costa Rica", "113"));
//            data.insertPerson1(new Person("56784", "PEPE", "Lopéz", "Caza", "14/06/97", "Costa Rica", "112"));
//            data.insertPerson1(new Person("56785", "PEPE", "Lopéz", "Caza", "14/06/97", "Costa Rica", "115"));
            ArrayList<ArrayList<Element>> lista = data.retorno();
            System.err.println(lista.get(1).size());

//            Person p[] = data.getAllPeople();
//            for (Person person : p) {
//                System.out.println(person.toString());
//            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Tarea7YerlinLeal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
