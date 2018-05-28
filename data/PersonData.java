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

    private boolean idParentExist(String id) {
        return true;
    }

    public void insertPerson1(Person person) throws IOException, IOException {
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

        Element eFatherId = new Element("fatherId");
        eFatherId.addContent(person.getFatherId());

        ePerson.addContent(eName);
        ePerson.addContent(eSurname1);
        ePerson.addContent(eSurname2);
        ePerson.addContent(eBirthdate);
        ePerson.addContent(eCountry);
        ePerson.addContent(eFatherId);
        
        if (idParentExist(person.getFatherId())) {
            List allElements = this.root.getChildren();
            int cont = 0;
            for (Object objectActual : allElements) {
                Element elementoActual = (Element) objectActual;
                if (elementoActual.getAttributeValue("id").equals(person.getFatherId())) {
                    elementoActual.addContent(ePerson);
                    break;
                }
                cont++;
            }
        } else {
            this.root.addContent(ePerson);
        }
        storeXML();
    }

    public void insertPerson(Person person) throws IOException {
        // insertamos en el documento en memoria
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

        Element eFatherId = new Element("fatherId");
        eFatherId.addContent(person.getFatherId());

        ePerson.addContent(eName);
        ePerson.addContent(eSurname1);
        ePerson.addContent(eSurname2);
        ePerson.addContent(eBirthdate);
        ePerson.addContent(eCountry);
        ePerson.addContent(eFatherId);

        this.root.addContent(ePerson); // agregar a root

        storeXML(); // guardamos todo
    } // inserta persona en el archivo

    public Person[] getAllPeople() {
        // obtenemos la cantidad de estudiantes
        int peopleQuantity = this.root.getContentSize();
        Person personArray[] = new Person[peopleQuantity];
        List elementList = this.root.getChildren(); // obtenemos lista con todos los elementos del root
        // recorrer la lista para ir creando el arreglo
        int count = 0;
        for (Object currentObject : elementList) {
            Element currentElement = (Element) currentObject; // transformo de objeto a element
            Person currentPerson = new Person();
            currentPerson.setId(currentElement.getAttributeValue("id"));
            currentPerson.setFirstName(currentElement.getChild("firstName").getValue());
            currentPerson.setSurname1(currentElement.getChild("surname1").getValue());
            currentPerson.setSurname2(currentElement.getChild("surname2").getValue());
            currentPerson.setBirthdate(currentElement.getChild("birthdate").getValue());
            currentPerson.setCountry(currentElement.getChild("country").getValue());
            currentPerson.setFatherId(currentElement.getChild("fatherId").getValue());
            personArray[count++] = currentPerson;
        }
        return personArray;
    } // metodo para obtener todos los objetos del xml

    public boolean deletePerson(String id) throws IOException {
        List allElements = this.root.getChildren();
        int cont = 0;
        for (Object objectActual : allElements) {
            Element elementoActual = (Element) objectActual;
            if (elementoActual.getAttributeValue("id").equals(id)) {
                this.root.removeContent(cont);
                storeXML();
                return true;
            }
            cont++;
        }
        return false;
    } // metodo para eliminar persona por id

    private boolean searchPerson(String id) {
        Person allPeople[] = getAllPeople();
        for (int i = 0; i < allPeople.length; i++) {
            if (allPeople[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    } // searchPerson

    public String updatePerson(Person newData) throws IOException {
        String message = "";
        if (!searchPerson(newData.getId())) {
            message = "The person does'not exist.";
        } else {
            Person allPeople[] = getAllPeople();
            Person person = new Person();
            for (int i = 0; i < allPeople.length; i++) {
                if (allPeople[i].getId().equals(newData.getId())) {
                    person = allPeople[i];
                    break;
                }
            }
            if (!newData.getFirstName().equals("")) {
                person.setFirstName(newData.getFirstName());
            }
            if (!newData.getSurname1().equals("")) {
                person.setSurname1(newData.getSurname1());
            }
            if (!newData.getSurname2().equals("")) {
                person.setSurname2(newData.getSurname2());
            }
            if (!newData.getBirthdate().equals("")) {
                person.setBirthdate(newData.getBirthdate());
            }
            if (!newData.getCountry().equals("")) {
                person.setCountry(newData.getCountry());
            }
            if (!newData.getFatherId().equals("")) {
                person.setFatherId(newData.getFatherId());
            }
            deletePerson(person.getId());
            insertPerson(person);
            message = "Success";
        }
        return message;
    }

} // end class
