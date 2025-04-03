package com.example.testtask.util;

import com.example.testtask.model.DetailedVacationRequest;
import com.example.testtask.model.NonDetailedVacationRequest;
import com.example.testtask.model.VacationRequest;

import java.time.LocalDate;

public class RequestsValidator {
    public static void validateRequest(VacationRequest vacationRequest) throws IllegalArgumentException{
        if (vacationRequest == null){
            throw new IllegalArgumentException("Vacation request is empty");
        }

        if (vacationRequest.getAverageSalary() < 0 ){
            throw new IllegalArgumentException("Average salary must be non-negative number");
        }

        if (vacationRequest instanceof NonDetailedVacationRequest){
            NonDetailedVacationRequest nonDetailedVacationRequest = (NonDetailedVacationRequest) vacationRequest;
            if (nonDetailedVacationRequest.getVacationInDays() < 0){
                throw new IllegalArgumentException("Total vacation days must be non-negative number");
            }
        }

        else if (vacationRequest instanceof DetailedVacationRequest){
            DetailedVacationRequest detailedVacationRequest = (DetailedVacationRequest) vacationRequest;

            LocalDate startDay = detailedVacationRequest.getVacationStartDay();
            LocalDate endDay = detailedVacationRequest.getVacationEndDay();

            if (startDay.isAfter(endDay) || startDay.equals(endDay)){
                throw new IllegalArgumentException("The vacation start day must be always before the end day");
            }

        }
    }
}
