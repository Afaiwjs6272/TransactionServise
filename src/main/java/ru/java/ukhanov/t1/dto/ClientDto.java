package ru.java.ukhanov.t1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String clientName;
    @Positive
    @Min(18)
    @Max(100)
    private Integer age;
    @Positive
    private BigDecimal totalBalance;
}