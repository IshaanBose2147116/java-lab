package lab2;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

public class SubjectAttendance extends Subject {
    Map<Date, Boolean> attendance;
    private int hoursAttended, hoursConducted;

    public SubjectAttendance(String subjectCode, String subName, byte credits, byte hours, 
        Teacher taughtBy, Date startsBy) {
            super(subjectCode, subName, credits, hours, taughtBy, startsBy);

            attendance = new HashMap<Date, Boolean>();
            this.hoursAttended = 0;
            this.hoursConducted = 0;
    }

    public SubjectAttendance(Subject subject) {
        super(subject);

        attendance = new HashMap<Date, Boolean>();
        this.hoursAttended = 0;
        this.hoursConducted = 0;
    }

    public SubjectAttendance(Subject subject, int hoursConducted, int hoursAttended) {
        super(subject);

        attendance = new HashMap<Date, Boolean>();
        this.hoursConducted = hoursConducted;
        this.hoursAttended = hoursAttended;
    }

    public int getHoursAttended() {
        return this.hoursAttended;
    }

    public int getHoursConducted() {
        return this.hoursConducted;
    }

    public void addLectureAttendance(Date conductedOn, boolean wasPresent) {
        this.attendance.put(conductedOn, wasPresent);
        this.hoursConducted += 1;
        this.hoursAttended += wasPresent ? 1 : 0;
    }

    public float getAttendancePercentage() {
        return (((float) this.hoursAttended) / this.hoursConducted) * 100;
    }
}
