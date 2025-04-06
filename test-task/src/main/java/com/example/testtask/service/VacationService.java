package com.example.testtask.service;

import com.example.testtask.model.DetailedVacationPayRequest;
import com.example.testtask.model.NonDetailedVacationPayRequest;
import com.example.testtask.model.VacationPayResponse;
import com.example.testtask.model.VacationPayRequest;
import com.example.testtask.util.DateChecker;
import com.example.testtask.util.RequestsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacationService {
    public static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private final DateChecker dateChecker;

    @Autowired
    public VacationService(DateChecker dateChecker){
        this.dateChecker = dateChecker;
    }

    public VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest) throws IllegalArgumentException{
        RequestsValidator.validateRequest(vacationPayRequest);
        
        int totalVacationDays;
        if (vacationPayRequest instanceof NonDetailedVacationPayRequest){
            totalVacationDays = ((NonDetailedVacationPayRequest) vacationPayRequest).getVacationInDays();
        }
        else {
            DetailedVacationPayRequest detailedVacationRequest = (DetailedVacationPayRequest) vacationPayRequest;
            totalVacationDays = dateChecker.getDaysWithoutHolidays(detailedVacationRequest.getVacationStartDay(), detailedVacationRequest.getVacationEndDay());
        }
        
        return new VacationPayResponse(vacationPayRequest.getAverageSalary() / AVERAGE_DAYS_IN_MONTH * totalVacationDays);
    }
}
