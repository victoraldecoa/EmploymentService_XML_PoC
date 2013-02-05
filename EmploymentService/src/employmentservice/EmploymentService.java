/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

// XSLT libraries
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import javax.xml.bind.Unmarshaller;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author victor
 */
public class EmploymentService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // DOM parsing to get the Transcript
            DOMParser domParser = new DOMParser();

            //Process the DOM tree, beginning with the
            // Document node to produce the output.
            // Invocation of processDocumentNode starts
            // a recursive process that processes the
            // entire DOM tree.
            Transcript transcript = domParser.processTranscript();
            System.out.println(transcript);
            
            EmploymentSAXParser saxParser = new EmploymentSAXParser();
            Employment[] employments = saxParser.parse();
            if (employments != null && employments.length > 0)
                System.out.println(employments[0]);
            
            // JAXB unmarshlling for classes Company and ShortCV
            JAXBContext context;
            Marshaller m;
            
            /* testing JAXB output
            Company company2 = new Company();
            company2.setName("TestCompany");
            company2.setYearFounded(1979);
            company2.setCountry("Latvia");
            
            context = JAXBContext.newInstance(Company.class);
            
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            m.marshal(company2, System.out);
            */
            
            context = JAXBContext.newInstance(Company.class);
            Unmarshaller um = context.createUnmarshaller();
            
            Company company = (Company)um.unmarshal(new FileReader("XMLs/Companies/Microsoft.xml"));
            
            System.out.println(company);
            
            // JAXB for SimpleCV
            /* output test
            ShortCV shortCV2 = new ShortCV();
            shortCV2.setFullname("Victor Aldecoa");
            shortCV2.setAddress("Kista allevag");
            shortCV2.setBirth("1989-12-16");
            shortCV2.setCity("Kista");
            shortCV2.setState("Stockholm");
            shortCV2.languageList = new ArrayList<Language>();
            Language lang = new Language();
            lang.setLangname("Brazilian Portuguese");
            lang.setReading("Fluent");
            lang.setWriting("Fluent");
            lang.setSpeaking("Fluent");
            lang.setUnderstanding("Fluent");
            
            Language lang2 = new Language();
            lang2.setLangname("English");
            lang2.setReading("Advanced");
            lang2.setWriting("Advanced");
            lang2.setSpeaking("Fluent");
            lang2.setUnderstanding("Fluent");
            
            shortCV2.languageList.add(lang);
            shortCV2.languageList.add(lang2);
            
            shortCV2.competences = new ArrayList<String>();
            shortCV2.competences.add("Java");
            shortCV2.competences.add("XML");
            
            context = JAXBContext.newInstance(ShortCV.class);
            
            m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            m.marshal(shortCV2, System.out);
            */
            
            context = JAXBContext.newInstance(ShortCV.class);
            
            um = context.createUnmarshaller();
            ShortCV shortCV = (ShortCV)um.unmarshal(new FileReader("XMLs/ShortCV.xml"));
            System.out.println(shortCV);
            
            // here we have everything needed to build the Applicant Profile
            ApplicantProfile ap = new ApplicantProfile();
            ap.competences = shortCV.competences;
            ap.languages = shortCV.languageList;
            ap.setAddress(shortCV.getAddress());
            ap.setBirth(shortCV.getBirth());
            ap.setCity(shortCV.getCity());
            ap.setName(shortCV.getFullname());
            ap.setNumber_worked_companies(employments.length);
            ap.setState(shortCV.getState());
            
            /*
            // calculating the GPA for the transcript
            float gpa = 0;
            float total_credits = 0;
            for (Course c : transcript.courses) {
                float grade = 0;
                
                if (c.grade.equals("A")) grade = 4;
                if (c.grade.equals("B")) grade = 3;
                if (c.grade.equals("C")) grade = 2;
                if (c.grade.equals("D")) grade = 1;
                if (c.grade.equals("E")) grade = 0.7f;
                
                gpa += c.credits * grade;
                total_credits += c.credits;
            }
            gpa /= total_credits;
            ap.setGPA_transcript(gpa);
            */
            
            // produce the output xml
            context = JAXBContext.newInstance(ApplicantProfile.class);
            
            m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            m.marshal(ap, new File("XMLs/ApplicantProfile_noGPA.xml"));
            
            
            // Adding the GPA to the Applicant Profile using XSLT
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslSource = new StreamSource("XMLs/GPA_Calc.xsl");
            Transformer transformer = tFactory.newTransformer(xslSource);
            transformer.transform(new StreamSource("XMLs/ApplicantProfile_noGPA.xml"), new StreamResult(new FileOutputStream("XMLs/ApplicantProfile.xml")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
