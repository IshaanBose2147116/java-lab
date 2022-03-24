package lab2;

public class Classes {
    int classID;
    char section;
    byte semester;
    Student[] students;
    Subject[] subjects;

    public Classes(int classID, char section, byte semester, Student[] students, Subject[] subjects) {
        this.classID = classID;
        this.section = section;
        this.semester = semester;
        this.students = students;
        this.subjects = subjects;
    }
}
