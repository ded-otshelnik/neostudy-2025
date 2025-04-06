package com.example.testtask.controller;

import com.example.testtask.model.VacationPayResponse;
import com.example.testtask.model.VacationPayRequest;
import com.example.testtask.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPayController {

    private final VacationService vacationService;

    @Autowired
    public VacationPayController(VacationService vacationService){
        this.vacationService = vacationService;
    }

    @GetMapping(value = "/calculate")
    public VacationPayResponse calculateVacationPay(@RequestBody VacationPayRequest vacationPayRequest){
        return vacationService.calculateVacationPay(vacationPayRequest);
    }
}
