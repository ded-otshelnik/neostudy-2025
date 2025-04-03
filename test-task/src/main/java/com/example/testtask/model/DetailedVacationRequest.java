package com.example.testtask.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(callSuper = false)
public class DetailedVacationRequest extends VacationRequest {
    @NonNull
    private final LocalDate vacationStartDay;
    @NonNull
    private final LocalDate vacationEndDay;

    public DetailedVacationRequest(Double averageSalary, @NonNull LocalDate vacationStartDay, @NonNull LocalDate vacationEndDay){
        super(RequestType.DETAILED, averageSalary);
        this.vacationStartDay = vacationStartDay;
        this.vacationEndDay = vacationEndDay;
    }
}
