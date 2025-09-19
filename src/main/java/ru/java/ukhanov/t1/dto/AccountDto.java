package ru.java.ukhanov.t1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    @Positive
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String clientName;
    @NotNull
    @Positive
    private Integer age;
    @Positive
    private Long accountNumber;
    @Past
    private LocalDate openingDate;
    @FutureOrPresent
    private LocalDate closingDate;
    @Positive
    private BigDecimal balance;
}