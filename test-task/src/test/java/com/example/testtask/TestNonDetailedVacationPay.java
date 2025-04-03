package com.example.testtask;

import com.example.testtask.model.NonDetailedVacationRequest;
import com.example.testtask.service.VacationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
class TestNonDetailedVacationPay {
    private final VacationService vacationService;

    @Autowired
    public TestNonDetailedVacationPay(VacationService vacationService){
        this.vacationService = vacationService;
    }

    @Test
    public void testCalculationOnCorrectValues(){
        // Arrange
        NonDetailedVacationRequest nonDetailedVacationRequest = new NonDetailedVacationRequest(293000.0, 28);

        double expected = 280000;

        // Act
        double actual = vacationService.calculateVacationPay(nonDetailedVacationRequest).getVacationPay();

        // Assert
        Assertions.assertEquals(expected, actual, String.format("Expected %f, got %f", expected, actual));
    }

    private static Stream<Arguments> provideArgumentsForCalculate() {
        return Stream.of(
                Arguments.of(new NonDetailedVacationRequest( -10000.0, 28), "AverageSalary must be non-negative number"),
                Arguments.of(new NonDetailedVacationRequest( -0.00001, 28), "AverageSalary must be non-negative number"),
                Arguments.of(new NonDetailedVacationRequest( -10000.0, -28), "AverageSalary must be non-negative number"),
                Arguments.of(new NonDetailedVacationRequest( 10000.0, -28), "VacationInDays must be non-negative number")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculate")
    public void testCalculationFallsOnNegativeNumbers(NonDetailedVacationRequest nonDetailedVacationRequest, String expectedMessage){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            vacationService.calculateVacationPay(nonDetailedVacationRequest);
        });
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
