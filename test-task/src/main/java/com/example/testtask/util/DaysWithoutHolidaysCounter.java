package com.example.testtask.util;

import com.example.testtask.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DaysWithoutHolidaysCounter {
    private final List<Holiday> holidays;

    @Autowired
    public DaysWithoutHolidaysCounter(List<Holiday> holidays){
        this.holidays = holidays;
    }
    
    private boolean isHoliday(LocalDate day){
        return holidays.stream().anyMatch(holiday -> holiday.isHoliday(day));
    }

    public int getDaysWithoutHolidays(LocalDate vacationStartDay, LocalDate endDay){
        // DaysWithoutHolidaysCounter looks over all days
        // between startDay and endDay including that days
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
