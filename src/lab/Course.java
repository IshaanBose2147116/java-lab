package lab;

import java.util.LinkedList;
import java.util.Iterator;

public class Course {
    int courseID;
    String courseName;
    byte duration;
    LinkedList<Classes> classes;
    float cost;
    Department offeredBy;

    public void printClasses() {
        Iterator<Classes> it = classes.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
