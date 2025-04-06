package com.example.testtask.util;

import com.example.testtask.model.DetailedVacationPayRequest;
import com.example.testtask.model.NonDetailedVacationPayRequest;
import com.example.testtask.model.VacationPayRequest;

import java.time.LocalDate;

public class RequestsValidator {
    public static void validateRequest(VacationPayRequest vacationPayRequest) throws IllegalArgumentException{
        if (vacationPayRequest == null){
            throw new IllegalArgumentException("Vacation request is empty");
        }

        if (vacationPayRequest.getAverageSalary() < 0 ){
            throw new IllegalArgumentException("Average salary must be non-negative number");
        }

        if (vacationPayRequest instanceof NonDetailedVacationPayRequest){
            NonDetailedVacationPayRequest nonDetailedVacationRequest = (NonDetailedVacationPayRequest) vacationPayRequest;
            if (nonDetailedVacationRequest.getVacationInDays() < 0){
                throw new IllegalArgumentException("Total vacation days must be non-negative number");
            }
        }

        else if (vacationPayRequest instanceof DetailedVacationPayRequest){
            DetailedVacationPayRequest detailedVacationRequest = (DetailedVacationPayRequest) vacationPayRequest;

            LocalDate startDay = detailedVacationRequest.getVacationStartDay();
            LocalDate endDay = detailedVacationRequest.getVacationEndDay();

            if (startDay.isAfter(endDay) || startDay.equals(endDay)){
                throw new IllegalArgumentException("The vacation start day must be always before the end day");
            }

        }
    }
}
