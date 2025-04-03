package com.example.testtask.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class NonDetailedVacationRequest extends VacationRequest {
    @NonNull
    private final Integer vacationInDays;

    public NonDetailedVacationRequest(Double averageSalary, @NonNull Integer vacationInDays){
        super(RequestType.NON_DETAILED, averageSalary);
        this.vacationInDays = vacationInDays;
    }
}
