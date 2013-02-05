/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor
 */
@XmlRootElement
public class ApplicantProfile {
    private String name;
    private String address;
    private String city;
    private String state;
    private String birth;
    
    /*
    private float GPA_transcript;
    
    public float getGPA_transcript() {
        return GPA_transcript;
    }
    
    public void setGPA_transcript(float GPA_transcript) {
        this.GPA_transcript = GPA_transcript;
    }
    */
    
    private int number_worked_companies;    
    
    @XmlElementWrapper(name = "languages")
    @XmlElement(name = "language")
    protected ArrayList<Language> languages;
    
    @XmlElementWrapper(name = "competences")
    @XmlElement(name = "competence")
    protected ArrayList<String> competences;

    public String getAddress() {
        return address;
    }

    public String getBirth() {
        return birth;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getNumber_worked_companies() {
        return number_worked_companies;
    }

    public String getState() {
        return state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber_worked_companies(int number_worked_companies) {
        this.number_worked_companies = number_worked_companies;
    }

    public void setState(String state) {
        this.state = state;
    }
}
