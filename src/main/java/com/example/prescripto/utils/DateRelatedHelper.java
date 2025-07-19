package com.example.prescripto.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class DateRelatedHelper {

    public static LocalDate[] getMonthRange(String monthName, int year) {
        Month month = Month.valueOf(monthName.toUpperCase());
        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return new LocalDate[] {startDate, endDate};
    }

    public static int getTotalDaysInMonth(String monthName, int year) {
        Month month = Month.valueOf(monthName.toUpperCase());
        YearMonth yearMonth = YearMonth.of(year, month);

        return yearMonth.lengthOfMonth();
    }
}
