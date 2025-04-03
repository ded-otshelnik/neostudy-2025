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
        @JsonSubTypes.Type(value = NonDetailedVacationRequest.class, name = "NON_DETAILED"),
        @JsonSubTypes.Type(value = DetailedVacationRequest.class, name = "DETAILED")
})
public class VacationRequest {
    @NonNull
    private RequestType requestType;
    @NonNull
    private final Double averageSalary;
}
