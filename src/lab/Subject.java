package lab;

import java.util.Date;

public class Subject {
    String subjectCode, subName;
    byte credits, hours;
    Teacher taughtBy;
    Date startsBy;

    public Subject(String subjectCode, String subName, byte credits, byte hours, Teacher taughtBy, Date startsBy) {
        this.subjectCode = subjectCode;
        this.subName = subName;
        this.credits = credits;
        this.hours = hours;
        this.taughtBy = taughtBy;
        this.startsBy = startsBy;
    }

    public Subject(Subject copy) {
        this.subjectCode = copy.subjectCode;
        this.subName = copy.subName;
        this.credits = copy.credits;
        this.hours = copy.hours;
        this.taughtBy = copy.taughtBy;
        this.startsBy = copy.startsBy;
    }

    public String getSubjectDetails() {
        return "Subject code: " + this.subjectCode
            + "\nSubject name: " + this.subName
            + "\nCredits: " + this.credits
            + "\nTaught by: " + this.taughtBy.getName();
    }
}
