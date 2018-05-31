/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Yerlin Leal
 */
public class PersonData {

    private Document document;
    private Element root;
    private String path;

    public PersonData() throws JDOMException, IOException {
        this.path = "people.xml";
        File file = new File(this.path);
        if (file.exists()) {
            // toma la extructura de datos y las carga en memoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            // cargar en memoria
            this.document = saxBuilder.build(this.path);
            this.root = this.document.getRootElement();
        } else {
            this.root = new Element("people"); // creamos el elemento raiz
            this.document = new Document(this.root); // creamos el documento
            storeXML(); // guardamos el documento
        }
    } // constructor

    private void storeXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(this.path));
    } // almacena en disco duro nuestro documento xml en la ruta especificada

    public Element getRoot() {
        return this.root;
    } // retorna el root

    public void insertPerson(Person person) throws IOException, IOException {
        if (idExist(root, person.getId())) {
            JOptionPane.showMessageDialog(null, "The id is already registered.", "Error", 0);
        } else {
            Element ePerson = new Element("person");
            ePerson.setAttribute("id", person.getId());

            Element eName = new Element("firstName");
            eName.addContent(person.getFirstName());

            Element eSurname1 = new Element("surname1");
            eSurname1.addContent(person.getSurname1());

            Element eSurname2 = new Element("surname2");
            eSurname2.addContent(person.getSurname2());

            Element eBirthdate = new Element("birthdate");
            eBirthdate.addContent(person.getBirthdate());

            Element eCountry = new Element("country");
            eCountry.addContent(person.getCountry());
            Element eParentId = new Element("parentId");
            eParentId.addContent(person.getParentId());

            ePerson.addContent(eName);
            ePerson.addContent(eSurname1);
            ePerson.addContent(eSurname2);
            ePerson.addContent(eBirthdate);
            ePerson.addContent(eCountry);
            ePerson.addContent(eParentId);

            if (idExist(root, person.getParentId())) {
                Element temp = getElement(root, person.getParentId());
                temp.addContent(ePerson);
            } else {
                this.root.addContent(ePerson);
            }
            storeXML();
            JOptionPane.showMessageDialog(null, "Success");
        } // else
    } // inserta nueva persona en el archivo

    public void printAll(Element e) {
        List all = e.getChildren("person");
        System.out.println(e.getAttributeValue("id"));
        for (int i = 0; i < all.size(); i++) {
            if (!((Element) all.get(i)).getChildren("person").isEmpty()) {
                printAll((Element) all.get(i));
            } else {
                System.out.println((((Element) all.get(i)).getAttributeValue("id")));
            }
        }
    } // imprime a todas las personas

    private Element getElement(Element e, String id) {
        Element temp = null;
        if (e.getAttributeValue("id") != null) {
            if (e.getAttributeValue("id").equals(id)) {
                return e;
            }
        }
        List all = e.getChildren("person");
        for (int i = 0; i < all.size(); i++) {
            if (!((Element) all.get(i)).getChildren("person").isEmpty()) {
                temp = getElement((Element) all.get(i), id);
                if (temp != null) {
                    return temp;
                }
            } else {
                if ((((Element) all.get(i)).getAttributeValue("id")).equals(id)) {
                    return (Element) all.get(i);
                }
            }
        } // for
        return null;
    } // retorna un Element segun id

    private boolean idExist(Element e, String id) {
        boolean temp = false;
        if (e.getAttributeValue("id") != null) {
            if (e.getAttributeValue("id").equals(id)) {
                return true;
            }
        }
        List all = e.getChildren("person");
        for (int i = 0; i < all.size(); i++) {
            if (!((Element) all.get(i)).getChildren("person").isEmpty()) {
                temp = idExist((Element) all.get(i), id);
                if (temp) {
                    return temp;
                }
            } else {
                if ((((Element) all.get(i)).getAttributeValue("id")).equals(id)) {
                    return true;
                }
            }
        } // for
        return false;
    } // retorna true si el id existe, sino retorna false

} // end class
