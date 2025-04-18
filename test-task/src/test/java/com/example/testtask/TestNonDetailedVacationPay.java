package com.example.testtask;

import com.example.testtask.exception.InvalidRequestException;
import com.example.testtask.model.NonDetailedVacationPayRequest;
import com.example.testtask.service.VacationPayService;
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
    private final VacationPayService vacationPayService;

    @Autowired
    public TestNonDetailedVacationPay(VacationPayService vacationPayService){
        this.vacationPayService = vacationPayService;
    }

    @Test
    public void testCalculationOnCorrectValues(){
        // Arrange
        NonDetailedVacationPayRequest nonDetailedVacationRequest = new NonDetailedVacationPayRequest(293000.0, 28);

        double expected = 280000;

        // Act
        double actual = vacationPayService.calculateVacationPay(nonDetailedVacationRequest).getVacationPay();

        // Assert
        Assertions.assertEquals(expected, actual, String.format("Expected %f, got %f", expected, actual));
    }

    private static Stream<Arguments> provideArgumentsForCalculate() {
        return Stream.of(
                Arguments.of(new NonDetailedVacationPayRequest( -10000.0, 28), "Average salary must be non-negative number"),
                Arguments.of(new NonDetailedVacationPayRequest( -0.00001, 28), "Average salary must be non-negative number"),
                Arguments.of(new NonDetailedVacationPayRequest( -10000.0, -28), "Average salary must be non-negative number"),
                Arguments.of(new NonDetailedVacationPayRequest( 10000.0, -28), "Total vacation days must be non-negative number")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculate")
    public void testCalculationFallsOnNegativeNumbers(NonDetailedVacationPayRequest nonDetailedVacationRequest, String expectedMessage){
        Exception exception = Assertions.assertThrows(InvalidRequestException.class, () ->{
            vacationPayService.calculateVacationPay(nonDetailedVacationRequest);
        });
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
