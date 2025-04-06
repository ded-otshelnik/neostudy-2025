package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "requestType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NonDetailedVacationPayRequest.class, name = "NON_DETAILED"),
        @JsonSubTypes.Type(value = DetailedVacationPayRequest.class, name = "DETAILED")
})
public class VacationPayRequest {
    // requestType needs for inheritance on REST API level
    @NonNull
    private RequestType requestType;
    @NonNull
    private final Double averageSalary;
}
