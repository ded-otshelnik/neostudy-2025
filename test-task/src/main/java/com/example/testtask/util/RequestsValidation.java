package com.example.testtask.util;

import com.example.testtask.exception.InvalidRequestException;
import com.example.testtask.model.DetailedVacationPayRequest;
import com.example.testtask.model.NonDetailedVacationPayRequest;
import com.example.testtask.model.RequestType;
import com.example.testtask.model.VacationPayRequest;

import java.time.LocalDate;

public class RequestsValidation {
    public static void validateRequest(VacationPayRequest vacationPayRequest) throws RuntimeException{
        if (vacationPayRequest == null){
            throw new InvalidRequestException("Vacation request is empty");
        }

        if (vacationPayRequest.getAverageSalary() < 0){
            throw new InvalidRequestException("Average salary must be non-negative number");
        }

        if (vacationPayRequest.getRequestType() == RequestType.NON_DETAILED){
            NonDetailedVacationPayRequest nonDetailedVacationRequest = (NonDetailedVacationPayRequest) vacationPayRequest;
            if (nonDetailedVacationRequest.getVacationInDays() < 0){
                throw new InvalidRequestException("Total vacation days must be non-negative number");
            }
        }

        else if (vacationPayRequest.getRequestType() == RequestType.DETAILED){
            DetailedVacationPayRequest detailedVacationRequest = (DetailedVacationPayRequest) vacationPayRequest;

            LocalDate startDay = detailedVacationRequest.getVacationStartDay();
            LocalDate endDay = detailedVacationRequest.getVacationEndDay();

            if (startDay.isAfter(endDay) || startDay.equals(endDay)){
                throw new InvalidRequestException("The vacation start day must be always before the end day");
            }

        }
    }
}
