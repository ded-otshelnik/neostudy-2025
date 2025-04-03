package com.example.testtask.service;

import com.example.testtask.model.DetailedVacationRequest;
import com.example.testtask.model.NonDetailedVacationRequest;
import com.example.testtask.model.VacationPayResponse;
import com.example.testtask.model.VacationRequest;
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

    public VacationPayResponse calculateVacationPay(VacationRequest vacationRequest) throws IllegalArgumentException{
        RequestsValidator.validateRequest(vacationRequest);
        
        int totalVacationDays;
        if (vacationRequest instanceof NonDetailedVacationRequest){
            totalVacationDays = ((NonDetailedVacationRequest) vacationRequest).getVacationInDays();
        }
        else {
            DetailedVacationRequest detailedVacationRequest = (DetailedVacationRequest) vacationRequest;
            totalVacationDays = dateChecker.getDaysWithoutHolidays(detailedVacationRequest.getVacationStartDay(), detailedVacationRequest.getVacationEndDay());
        }
        
        return new VacationPayResponse(vacationRequest.getAverageSalary() / AVERAGE_DAYS_IN_MONTH * totalVacationDays);
    }
}
