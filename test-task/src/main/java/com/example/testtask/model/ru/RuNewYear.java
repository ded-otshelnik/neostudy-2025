package com.example.testtask.model.ru;

import com.example.testtask.model.Holiday;

import java.time.LocalDate;
import java.time.Month;

public class RuNewYear implements Holiday {

    @Override
    public boolean isHoliday(LocalDate day) {
        return isInNewYearHolidays(day);
    }

    private boolean isInNewYearHolidays(LocalDate day){
        if (!isInJanuary(day)){
            return false;
        }

        // in Russia New Year holidays last 8 days including New Year day
        int NEW_YEAR_DAY = 1;
        int NEW_YEAR_HOLIDAYS_END_DAY = 9;
        if (LocalDate.of(day.getYear(), Month.JANUARY, NEW_YEAR_DAY).isBefore(day) &&
                LocalDate.of(day.getYear(), Month.JANUARY, NEW_YEAR_HOLIDAYS_END_DAY).isAfter(day)){
            return true;
        }

        // also the day before New Year is mostly holiday
        return isDayBeforeNewYear(day);
    }

    private boolean isInJanuary(LocalDate day){
        return day.getMonth().equals(Month.JANUARY);
    }

    private boolean isInDecember(LocalDate day) {
        return day.getMonth().equals(Month.DECEMBER);
    }

    private boolean isDayBeforeNewYear(LocalDate day){
        if (!isInDecember(day)){
            return false;
        }

        // day before new year always in previous year and mostly it is a holiday
        LocalDate dayBeforeNewYear = LocalDate.of(day.getYear() - 1, Month.DECEMBER, 31);
        return dayBeforeNewYear.equals(day);
    }
}
