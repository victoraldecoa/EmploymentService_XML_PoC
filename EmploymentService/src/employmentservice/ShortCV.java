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
@XmlRootElement(namespace = "employmentservice")
public class ShortCV {
    private String fullname;
    private String birth;
    private String address;
    private String city;
    private String state;
    
    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "languageList")
    @XmlElement(name = "language")
    protected ArrayList<Language> languageList;
    
    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "competences")
    @XmlElement(name = "competence")
    protected ArrayList<String> competences;
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if (languageList != null && languageList.size() > 0) {
            Language lang =  languageList.get(0);
            return "CV: " + fullname + " speaks " +  lang.getSpeaking() + " " + lang.getLangname();
        } else {
            return "CV: " + fullname;
        }
    }
    
    
}
