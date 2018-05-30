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

//        Element eName = new Element("firstName");
//        eName.addContent(person.getFirstName());
//
//        Element eSurname1 = new Element("surname1");
//        eSurname1.addContent(person.getSurname1());
//
//        Element eSurname2 = new Element("surname2");
//        eSurname2.addContent(person.getSurname2());
//
//        Element eBirthdate = new Element("birthdate");
//        eBirthdate.addContent(person.getBirthdate());
//
//        Element eCountry = new Element("country");
//        eCountry.addContent(person.getCountry());
        Element eParentId = new Element("parentId");
        eParentId.addContent(person.getParentId());

//        ePerson.addContent(eName);
//        ePerson.addContent(eSurname1);
//        ePerson.addContent(eSurname2);
//        ePerson.addContent(eBirthdate);
//        ePerson.addContent(eCountry);
        ePerson.addContent(eParentId);

        if (idParentExist(person.getParentId())) {
            List allElements = this.root.getChildren();
            int cont = 0;
            for (Object objectActual : allElements) {
                Element elementoActual = (Element) objectActual;
                if (elementoActual.getAttributeValue("id").equals(person.getParentId())) {
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
            currentPerson.setParentId(currentElement.getChild("parentId").getValue());
            personArray[count++] = currentPerson;
        }
        return personArray;
    } // metodo para obtener todos los objetos del xml

    public Element getRoot() {
        return this.root;
    }

    public void printAll(Element e) {
        List all = e.getChildren("person");
        System.out.println(e.getAttributeValue("id"));
        for (int i = 0; i < all.size(); i++) {
            if (((Element) all.get(i)).getChildren("person").size() != 0) {
                printAll((Element) all.get(i));
            } else {
//                String id1 = (((Element)all.get(i)).getAttributeValue("id"));
                System.out.println((((Element) all.get(i)).getAttributeValue("id")));
            }
        }
    }

    public Element getElement(Element e, String id) {
        System.out.println(e.getAttributeValue("id"));
        List all = e.getChildren("person");
        for (int i = 0; i < all.size(); i++) {
            System.out.println("i:" + i);
            if (((Element) all.get(i)).getChildren("person").size() != 0) {
                System.out.println("lista!=0");
                return getElement((Element) all.get(i), id);
            } else {
                System.out.println("lista vacia");
                if ((((Element) all.get(i)).getAttributeValue("id")).equals(id)) {
                    return (Element) all.get(i);
                }
            } // else
            System.out.println("emm");
        } // for
        System.out.println("NOOOOOOO");
        return null;
    }

    public boolean existe(Element e, String id) {
//        System.out.println(e.getAttributeValue("id"));
        if (e.getAttributeValue("id") != null) {
            if (e.getAttributeValue("id").equals(id)) {
                return true;
            }
        }
        List all = e.getChildren("person");
        for (int i = 0; i < all.size(); i++) {
            System.out.println("i:" + i);
            if (((Element) all.get(i)).getChildren("person").size() != 0) {
                System.out.println("lista!=0");
                return existe((Element) all.get(i), id);
            } else {
                if ((((Element) all.get(i)).getAttributeValue("id")).equals(id)) {
                    System.out.println("\n\nSI RETORNA EN HIJOS");
                    return true;
                }
            } // else
            System.out.println("emm");
        } // for
        System.out.println("NO DEBE SALIR");
        return false;
    }

    public void printNumHijos(Element e) {
        System.out.println(e.getChildren("person").size());
    }

} // end class
