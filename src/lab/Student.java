package lab;

import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

interface Age {
    LocalDate currentDate = LocalDate.now();
    int getAge();
}

public class Student {
    private StringBuffer fname, lname;
    private Date dob;
    private int studentID;
    public static String university;
    public StringBuilder address;
    Classes attends;
    Course course;
    ArrayList<SubjectAttendance> attendance;

    static {
        Student.university = "Christ University";
    }

    static class DateCreator {
        static Date createDate(int year, int month, int day) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DATE, day);
    
            return cal.getTime();
        }

        static Date createDate(String year, String month, String day) {
            return DateCreator.createDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        }
    
        static Date createDate(int year, int month, int day, int hour, int minute, int second) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DATE, day);
            cal.set(Calendar.HOUR, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
    
            return cal.getTime();
        }
    }

    public Student(String fname, String lname, String dob, int studentID, Classes attends, Course course, ArrayList<SubjectAttendance> attendance, String address) {
        this.fname = new StringBuffer(fname);
        this.lname = new StringBuffer(lname);

        String[] dates = dob.split("/");
        this.dob = DateCreator.createDate(dates[0], dates[1], dates[2]);

        this.studentID = studentID;
        this.attends = attends;
        this.course = course;
        this.attendance = attendance;
        this.address = new StringBuilder(address);
    }

    public Student(String name, String dob, int studentID, Classes attends, Course course, ArrayList<SubjectAttendance> attendance, String address) {
        this(name.split(" ")[0], name.split(" ")[1], dob, studentID, attends, course, attendance, address);
    }

    public StringBuffer getStudentName() {
        return this.fname.append(this.lname);
    }

    public int getStudentAge() throws AgeException {
        Age age = new Age() {
            public int getAge() {
                final LocalDate studentDoB = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                final Period difference = Period.between(studentDoB, currentDate);

                return difference.normalized().getYears();
            }
        };

        if (age.getAge() <= 18)
            throw new AgeException("Age cannot be less than or equal to 18 years.");

        return age.getAge();
    }

    private void addAttendance() {
        this.attendance.get(0).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 20, 11, 0, 0), true
        );
        this.attendance.get(0).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 20, 12, 0, 0), true
        );
        this.attendance.get(0).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 22, 9, 0, 0), false
        );
        this.attendance.get(0).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 23, 13, 0, 0), true
        );

        this.attendance.get(1).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 20, 11, 0, 0), false
        );
        this.attendance.get(1).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 20, 12, 0, 0), false
        );
        this.attendance.get(1).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 22, 9, 0, 0), false
        );
        this.attendance.get(1).addLectureAttendance(
            Student.DateCreator.createDate(2022, 3, 23, 13, 0, 0), true
        );
    }

    public void printStudentAttendancePercentage() {
        int totalConducted = 0, totalAttended = 0;

        Iterator<SubjectAttendance> it = attendance.iterator();

        while (it.hasNext()) {
            SubjectAttendance temp = it.next();
            totalConducted += temp.getHoursConducted();
            totalAttended += temp.getHoursAttended();
        }

        System.out.println("Total attendance percentage: " + (((float) totalAttended / totalConducted) * 100) + "%");
    }

    public String toString() {
        return "Name: " + this.getStudentName()
            + "\nStudentID: " + this.studentID;
    }

    public static void main(String[] args) {
        Subject java = new Subject("MCA 372", "Java Programming", (byte) 4, (byte) 120, null, null);
        Subject ai = new Subject("MCA 341B", "Introduction to AI", (byte) 4, (byte) 120, null, null);
        Subject cn = new Subject("MCA 331", "Computer Networks", (byte) 4, (byte) 120, null, null);
        Subject ds = new Subject("MCA 371", "Data Structures in C", (byte) 4, (byte) 120, null, null);
        TreeSet<Subject> subjects = new TreeSet<Subject>();
        subjects.add(java);
        subjects.add(ai);
        subjects.add(cn);
        subjects.add(ds);

        for (Subject sub : subjects) {
            System.out.println(sub);
        }
        
        SubjectAttendance javaAttendance = new SubjectAttendance(java);
        SubjectAttendance aiAttendance = new SubjectAttendance(ai);
        SubjectAttendance cnAttendance = new SubjectAttendance(cn);
        SubjectAttendance dsAttendance = new SubjectAttendance(ds);

        SubjectAttendance[] attendance = { javaAttendance, cnAttendance, dsAttendance, aiAttendance };
        ArrayList<SubjectAttendance> attList = new ArrayList<SubjectAttendance>(Arrays.asList(attendance));
        int classID = 0;
        char section = '\0';
        byte semester = 0;
        BufferedReader reader = null;

        try {
            File file = new File("./data/classinfo.txt");
            FileReader fileReader = new FileReader(file.getAbsolutePath());
            reader = new BufferedReader(fileReader);
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                switch (lineNum) {
                    case 0: classID = Integer.parseInt(line); break;
                    case 1: section = line.charAt(0); break;
                    case 2: semester = Byte.parseByte(line); break;
                }

                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("classinfo.txt not found.");
            return;
        } catch (IOException e) {
            System.out.println("Could not read file.");
            return;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Could not close buffered reader.");
            } catch (NullPointerException e) {}
        }

        Classes studentClass = new Classes(classID, section, semester, null, subjects);

        try {
            Student student = new Student(args[0], "2000/04/28", Integer.parseInt(args[1]), studentClass, new Course(), attList, "903 Address...");
            student.addAttendance();
            student.printStudentAttendancePercentage();
            
            try {
                System.out.println("AGE: " + student.getStudentAge());
            } catch (AgeException e) {
                System.out.println(e.getMessage());
            }
    
            System.out.println("Student details:");
            System.out.println(student);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Must pass two command line arguments.");
        } catch (NumberFormatException e) {
            System.out.println("Second argument must be an integer.");
        } finally {
            System.out.println("Execution finished!");
        }
    }
}
