package com.example.testtask.model.ru;

import com.example.testtask.model.Holiday;

import java.time.LocalDate;
import java.time.Month;

public class RuVictoryDay implements Holiday {

    private static final int VICTORY_DAY = 9;

    @Override
    public boolean isHoliday(LocalDate day) {
        LocalDate VictoryDay = LocalDate.of(day.getYear(), Month.MAY, VICTORY_DAY);
        return day.equals(VictoryDay);
    }
}
