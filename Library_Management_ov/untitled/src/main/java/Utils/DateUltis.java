package Utils;

import java.time.LocalDate;
import java.time.Period;

public class DateUltis {
    public static double getDateBetwen(LocalDate date1, LocalDate date2){
        Period period = Period.between(date1, date2);
        double totalDays = 0;
        for (int i = 0; i < period.getMonths(); i++) {
            LocalDate tempDate = date1.plusMonths(i);
            int daysInMonth = tempDate.lengthOfMonth();
            totalDays += daysInMonth;
        }
        totalDays += period.getDays();
        return totalDays;
    }
    public static int getDate(LocalDate day1, LocalDate day2){
        Period period = day1.until(day2);
        return period.getDays();
    }
}
