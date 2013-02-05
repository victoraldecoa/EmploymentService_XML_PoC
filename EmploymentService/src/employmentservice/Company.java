/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor
 */
@XmlRootElement(namespace = "employmentservice")
public class Company {
    private String name;
    private int yearFounded;
    private String country;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }
    
    public int getYearFounded() {
        return yearFounded;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Company: " + name + ", founded in " + yearFounded + ", from " + country;
    }
}
