package lab;

import java.time.LocalDate;

interface Age {
    LocalDate currentDate = LocalDate.now();
    int getAge();
}
