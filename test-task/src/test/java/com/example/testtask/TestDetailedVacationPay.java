package com.example.testtask;

import com.example.testtask.model.DetailedVacationRequest;
import com.example.testtask.service.VacationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

@SpringBootTest
public class TestDetailedVacationPay {
    private final VacationService vacationService;

    @Autowired
    public TestDetailedVacationPay(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    // additional function that calculates number of days within interval
    public static long daysBetweenInclusive(LocalDate start, LocalDate end) {
        return Math.abs(ChronoUnit.DAYS.between(start, end)) + 1;
    }

    @Test
    public void testCalculationOnCorrectValues() {
        // Arrange
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 4, 1);
        int holidayDaysAmount = 8;

        DetailedVacationRequest detailedVacationRequest = new DetailedVacationRequest(293000.0,
                startDate, endDate);

        double expected = (daysBetweenInclusive(endDate, startDate) - holidayDaysAmount) * 293000.0 / VacationService.AVERAGE_DAYS_IN_MONTH;

        // Act
        double actual = vacationService.calculateVacationPay(detailedVacationRequest).getVacationPay();

        // Assert
        Assertions.assertEquals(expected, actual, String.format("expected: %f, got: %f", expected, actual));
    }

    private static Stream<Arguments> provideArgumentsForCalculate() {
        return Stream.of(
                Arguments.of(new DetailedVacationRequest(10000.0,
                            LocalDate.of(2025, 2, 1), LocalDate.of(2024, 2, 1)
                        ),
                        "Start date must be always before end date"),
                Arguments.of(new DetailedVacationRequest( -0.00001,
                            LocalDate.of(2025, 2, 1), LocalDate.of(2024, 2, 1)
                        ),
                        "AverageSalary must be non-negative number"),
                Arguments.of(new DetailedVacationRequest( 10000.0,
                                LocalDate.of(2025, 2, 1), LocalDate.of(2024, 2, 1)
                        ),
                        "Start date must be always before end date")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculate")
    public void testCalculationFallsOnNegativeNumbers(DetailedVacationRequest detailedVacationRequest, String expectedMessage){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            vacationService.calculateVacationPay(detailedVacationRequest);
        });
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
