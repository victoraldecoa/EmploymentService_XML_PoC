/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor
 */
@XmlRootElement(name = "language")
public class Language {
    private String langname;
    
    private String speaking;
    private String understanding;
    private String writing;
    private String reading;

    @XmlAttribute(name = "name")
    public String getLangname() {
        return langname;
    }

    public String getReading() {
        return reading;
    }

    public String getSpeaking() {
        return speaking;
    }

    public String getUnderstanding() {
        return understanding;
    }

    public String getWriting() {
        return writing;
    }

    public void setLangname(String langname) {
        this.langname = langname;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    public void setUnderstanding(String understanding) {
        this.understanding = understanding;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }
    
    
}
