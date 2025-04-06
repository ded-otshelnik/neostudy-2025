package com.example.testtask.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class NonDetailedVacationPayRequest extends VacationPayRequest {
    @NonNull
    private final Integer vacationInDays;

    public NonDetailedVacationPayRequest(Double averageSalary, @NonNull Integer vacationInDays){
        super(RequestType.NON_DETAILED, averageSalary);
        this.vacationInDays = vacationInDays;
    }
}
