package com.example.testtask.util;

import com.example.testtask.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DateChecker {
    // date checker
    private final List<Holiday> holidays;

    @Autowired
    public DateChecker(List<Holiday> holidays){
        this.holidays = holidays;
    }
    
    private boolean isHoliday(LocalDate day){
        return holidays.stream().anyMatch(holiday -> holiday.isHoliday(day));
    }

    public int getDaysWithoutHolidays(LocalDate vacationStartDay, LocalDate endDay){
        // DateChecker look over all days between vacationStartDay and endDay
        LocalDate checkedDay = vacationStartDay;
        int totalDaysWithoutHolidays = 0;

        while (checkedDay.isBefore(endDay)){
            if (!isHoliday(checkedDay)){
                totalDaysWithoutHolidays++;
            }
            checkedDay = checkedDay.plusDays(1);
        }

        return totalDaysWithoutHolidays;
    }
}
