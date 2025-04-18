package com.example.testtask.controller;

import com.example.testtask.model.VacationPayResponse;
import com.example.testtask.model.VacationPayRequest;
import com.example.testtask.service.VacationPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @Autowired
    public VacationPayController(VacationPayService vacationPayService){
        this.vacationPayService = vacationPayService;
    }

    @GetMapping(value = "/calculate")
    public VacationPayResponse calculateVacationPay(@RequestBody VacationPayRequest vacationPayRequest){
        return vacationPayService.calculateVacationPay(vacationPayRequest);
    }
}
