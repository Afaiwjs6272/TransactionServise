package ru.java.ukhanov.t1.model.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountMessage {
    @JsonProperty("number")
    private Long number;

    @JsonProperty("openingDate")
    private LocalDate openingDate;

    @JsonProperty("closingDate")
    private LocalDate closingDate;

    @JsonProperty("balance")
    private BigDecimal balance;
}