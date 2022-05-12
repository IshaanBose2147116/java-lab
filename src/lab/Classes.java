package lab;

import java.util.HashSet;
import java.util.TreeSet;

public class Classes {
    int classID;
    char section;
    byte semester;
    HashSet<Student> students;
    TreeSet<Subject> subjects;

    public Classes(int classID, char section, byte semester, HashSet<Student> students, TreeSet<Subject> subjects) {
        this.classID = classID;
        this.section = section;
        this.semester = semester;
        this.students = students;
        this.subjects = subjects;
    }
}
