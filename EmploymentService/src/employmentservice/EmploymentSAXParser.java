/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author victor
 */
public class EmploymentSAXParser {
    
    private SAXParserFactory saxpf = SAXParserFactory.newInstance();
    private SAXParser saxp;
    
    public EmploymentSAXParser() {

    }
    
    public Employment[] parse() {
        Employment[] result = null;
        // Create Parser
        try {
            saxp = saxpf.newSAXParser();
            saxpf.setNamespaceAware(true);
            saxpf.setValidating(true);

            MyParser parser = new MyParser();
            
            saxp.parse("XMLs/Employment.xml", parser);
            
            result = parser.getEmployments();
            
        } catch (SAXNotRecognizedException ex) {
            Logger.getLogger(EmploymentSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmploymentSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EmploymentSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(EmploymentSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        return result;
    }
    
    static class MyParser extends DefaultHandler {
        public MyParser() {
            list_employment = new ArrayList<Employment>();
        }
        
        public Employment[] getEmployments() {
            return list_employment.toArray(new Employment[list_employment.size()]);
        }

        private ArrayList<Employment> list_employment;
        private Employment employment;
        
        private String element;
        
        @Override
        public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
            element = arg2;            
        }

        @Override
        public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
            if (element.equals("employment")) {
                employment = new Employment();
            } else if (element.equals("company-name")) {
                employment.company_name = new String(arg0, arg1, arg2);
            } else if (element.equals("date-started")) {
                employment.started = new String(arg0, arg1, arg2);
            } else if (element.equals("date-ended")) {
                employment.ended = new String(arg0, arg1, arg2);
            }
        }

        @Override
        public void endElement(String arg0, String arg1, String arg2) throws SAXException {
            if (arg2.equals("employment")) {
                list_employment.add(employment);
            }
            element = "";
        }
        
        @Override
        public void startDocument() throws SAXException {
        }
        @Override
        public void endDocument() throws SAXException {
        }
    }
}
