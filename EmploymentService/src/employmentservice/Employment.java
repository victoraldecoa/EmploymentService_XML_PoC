/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

/**
 *
 * @author victor
 */
public class Employment {
    public String company_name;
    public String started;
    public String ended;

    @Override
    public String toString() {
        return "Last employment: " + company_name + " from " + started + " to " + ended;
    }
}
