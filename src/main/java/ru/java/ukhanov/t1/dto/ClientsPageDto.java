package ru.java.ukhanov.t1.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientsPageDto {
    private List<ClientDto> clients;
    @Positive
    private int page;
    @Positive
    private int size;
    @Positive
    private long totalElements;
    @Positive
    private int totalPages;
}