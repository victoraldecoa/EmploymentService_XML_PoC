/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employmentservice;

/**
 *
 * @author victor
 */
public class Transcript {
    public String name;
    public String university;
    public String degree;
    public int year;
    public Course[] courses;

    @Override
    public String toString() {
        return "Transcript: " + name + ", " + university + " , " + degree + ", " + year;
    }
}
