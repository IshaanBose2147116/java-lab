package lab;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.util.Calendar;

public class Student implements Age, Name, Runnable {
    private StringBuffer fname, lname;
    private Date dob;
    private int studentID;
    public static String university;
    public StringBuilder address;
    Classes attends;
    Course course;
    SubjectAttendance[] attendance;
    volatile int attendanceIndex;
    volatile boolean inCritSection = false;

    static {
        Student.university = "Christ University";
    }

    public Student(String fname, String lname, String dob, int studentID, Classes attends, Course course, SubjectAttendance[] attendance, String address) {
        this.fname = new StringBuffer(fname);
        this.lname = new StringBuffer(lname);

        String[] dates = dob.split("/");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dates[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(dates[1]));
        cal.set(Calendar.DATE, Integer.parseInt(dates[2]));
        this.dob = cal.getTime();

        this.studentID = studentID;
        this.attends = attends;
        this.course = course;
        this.attendance = attendance;
        this.address = new StringBuilder(address);
    }

    public Student(String name, String dob, int studentID, Classes attends, Course course, SubjectAttendance[] attendance, String address) {
        this(name.split(" ")[0], name.split(" ")[1], dob, studentID, attends, course, attendance, address);
    }

    public StringBuffer getStudentName() {
        return this.fname.append(this.lname);
    }

    public int getAge() {
        final LocalDate studentDoB = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final Period difference = Period.between(studentDoB, Age.currentDate);

        return difference.getYears();
    }

    private void addAttendance() {
        this.attendance[0].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 20, 11, 0, 0), true
        );
        this.attendance[0].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 20, 12, 0, 0), true
        );
        this.attendance[0].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 22, 9, 0, 0), false
        );
        this.attendance[0].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 23, 13, 0, 0), true
        );

        this.attendance[1].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 20, 11, 0, 0), false
        );
        this.attendance[1].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 20, 12, 0, 0), false
        );
        this.attendance[1].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 22, 9, 0, 0), false
        );
        this.attendance[1].addLectureAttendance(
            lab.Age.DateCreator.createDate(2022, 3, 23, 13, 0, 0), true
        );
    }

    @Override
    public String getFullname() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String getFirstName() {
        return this.fname.toString();
    }

    @Override
    public String getLastName() {
        return this.lname.toString();
    }

    public void printStudentAttendancePercentage() {
        int totalConducted = 0, totalAttended = 0;

        for (int i = 0; i < attendance.length; i++) {
            totalConducted += this.attendance[i].getHoursConducted();
            totalAttended += this.attendance[i].getHoursAttended();
        }

        System.out.println("Total attendance percentage: " + (((float) totalAttended / totalConducted) * 100) + "%");
    }

    public String toString() {
        return "Name: " + this.getStudentName()
            + "\nStudentID: " + this.studentID;
    }

    synchronized private void printAttendances() {
        try {
            while (inCritSection)
                this.wait();
            inCritSection = true;
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println(this.attendance[attendanceIndex]);
        System.out.println();
        attendanceIndex += 1;
        inCritSection = false;

        if (attendanceIndex < attendance.length - 1)
            this.notify();
        else
            this.notifyAll();
    }

    @Override
    public void run() {
        printAttendances();
    }

    public static void main(String[] args) throws InterruptedException {
        Subject java = new Subject("MCA 372", "Java Programming", (byte) 4, (byte) 120, null, null);
        Subject ai = new Subject("MCA 341B", "Introduction to AI", (byte) 4, (byte) 120, null, null);
        Subject cn = new Subject("MCA 331", "Computer Networks", (byte) 4, (byte) 120, null, null);
        Subject ds = new Subject("MCA 371", "Data Structures in C", (byte) 4, (byte) 120, null, null);
        Subject[] subjects = { java, ai, cn, ds };
        
        SubjectAttendance javaAttendance = new SubjectAttendance(java);
        SubjectAttendance aiAttendance = new SubjectAttendance(ai);
        SubjectAttendance cnAttendance = new SubjectAttendance(cn);
        SubjectAttendance dsAttendance = new SubjectAttendance(ds);
        
        SubjectAttendance[] attendance = { javaAttendance, cnAttendance, dsAttendance, aiAttendance };
        
        Classes studentClass = new Classes(2147, 'A', (byte) 3, null, subjects);
        
        Student student = new Student(args[0], "2000/04/28", Integer.parseInt(args[1]), studentClass, new Course(), attendance, "903 Address...");
        student.addAttendance();
        javaAttendance.start();
        aiAttendance.start();
        cnAttendance.start();
        dsAttendance.start();

        javaAttendance.join();

        student.printStudentAttendancePercentage();
        
        System.out.println("AGE: " + student.getAge());

        System.out.println("Student details:");
        System.out.println(student);

        Thread.sleep(1000);

        Thread t1 = new Thread(student);
        Thread t2 = new Thread(student);
        Thread t3 = new Thread(student);
        Thread t4 = new Thread(student);

        System.out.println();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
