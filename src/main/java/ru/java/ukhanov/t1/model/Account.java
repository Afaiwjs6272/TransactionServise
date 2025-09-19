package ru.java.ukhanov.t1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    private Long id;
    private String clientId;
    private Long number;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account(String clientId, Long number, LocalDate openingDate, LocalDate closingDate, BigDecimal balance) {
        this.clientId = clientId;
        this.number = number;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
    }
}