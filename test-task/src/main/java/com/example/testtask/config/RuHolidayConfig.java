package com.example.testtask.config;

import com.example.testtask.model.Holiday;
import com.example.testtask.model.ru.RuNewYear;
import com.example.testtask.model.ru.RuVictoryDay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RuHolidayConfig {
    @Bean
    public List<Holiday> getRussianHolidays(){
        return List.of(
                new RuNewYear(),
                new RuVictoryDay()
        );
    }
}
