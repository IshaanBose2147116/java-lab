package lab;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

interface Age {
    LocalDate currentDate = LocalDate.now();
    
    int getAge();
    default String getCurrentDate() {
        return currentDate.toString();
    }

    interface DateCreator {
        static Date createDate(int year, int month, int day) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, day);
    
            return cal.getTime();
        }

        static Date createDate(int year, int month, int day, int hour, int minute, int second) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, day);
            cal.set(Calendar.HOUR, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
    
            return cal.getTime();
        }
    }
}
