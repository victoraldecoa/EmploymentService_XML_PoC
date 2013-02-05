/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

// DOM libraries
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author victor
 */
public class DOMParser {
    private Document document;
    
    public DOMParser() throws ParserConfigurationException, SAXException, IOException {
                // Parsing Transcript using DOM
            
            //Get a factory object for DocumentBuilder objects
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            // to make the parser a validating parse
            factory.setValidating(true);
            //To parse a XML document with a namespace,
            factory.setNamespaceAware(true);

            // to ignore cosmetic whitespace between elements.
            factory.setIgnoringElementContentWhitespace(true);
            factory.setAttribute(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            //specifies the XML schema document to be used for validation.
            factory.setAttribute(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "Transcript.xsd");

            //Get a DocumentBuilder (parser) object

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            //Parse the XML input file to create a
            // Document object that represents the
            // input XML file.
            ///
            document = builder.parse(
                    new File("XMLs/Transcript.xml"));
    }
    
    Node findNode(NodeList nodes, String nodeName) {
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName().equals(nodeName)) {
                return nodes.item(i);
            }
        }
        return null;
    }

    String getNodeValue(NodeList nodes, String nodeName) {
        Node result = findNode(nodes, nodeName);
        if (result != null) {
            return result.getFirstChild().getNodeValue();
        }
        return null;
    }

    void processNode(Node root) {

        try {
            if (root == null) {
                System.err.println("Node is null !!!");
                return;
            }

            int type = root.getNodeType();

            switch (type) {
                case Node.TEXT_NODE: {
                    System.out.print("Text Node: <" + root.getNodeValue() + ">\n");

                    break;
                }//end case Node.TEXT_NODE

                case Node.ATTRIBUTE_NODE: {
                    System.out.print("Attribute Node: <" + root.getNodeName() + " = " + root.getNodeValue() + " >\n");
                    break;
                }

                case Node.ELEMENT_NODE: {
                    NodeList children = root.getChildNodes();
                    System.out.print("Element Node: <" + root.getNodeName() + "> has " + children.getLength() + " child elements.\n");


                    for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
                        processNode(child);
                    }
                    break;
                }//end case ELEMENT_NODE

                case Node.DOCUMENT_NODE: {
                    NodeList children = root.getChildNodes();
                    System.out.print("Document Node: <" + root.getNodeName() + "> has " + children.getLength() + " child elements.\n");

                    for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
                        processNode(child);
                    }

                    break;
                }//end case DOCUMENT_NODE

                // there are even more cases to check
                default: {
                }//end default

            }//end switch

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }//end catch
    }

    Transcript processTranscript() {
        Node root = document.getFirstChild();
        Transcript result = null;
        try {
            if (root.getNodeName().equals("transcript")) {
                result = new Transcript();
                result.name = getNodeValue(root.getChildNodes(), "name");
                result.university = getNodeValue(root.getChildNodes(), "university");
                result.degree = getNodeValue(root.getChildNodes(), "degree");
                result.year = Integer.parseInt(getNodeValue(root.getChildNodes(), "year"));
                NodeList courseList = findNode(root.getChildNodes(), "courses").getChildNodes();
                result.courses = new Course[courseList.getLength()];

                for (int i = 0; i < courseList.getLength(); i++) {
                    Node now = courseList.item(i);
                    result.courses[i] = new Course();
                    
                    result.courses[i].name = getNodeValue(now.getChildNodes(),
                                                          "course-name");
                    result.courses[i].grade = getNodeValue(now.getChildNodes(),
                                                          "grade");
                    result.courses[i].credits = Float.parseFloat(getNodeValue(courseList.item(i).getChildNodes(),
                                                          "credits"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        return result;
    }
}
