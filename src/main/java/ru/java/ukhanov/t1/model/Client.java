package ru.java.ukhanov.t1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    private String clientId;
    private String clientName;
    private Integer age;
    private Integer departmentId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Client(String clientId, String clientName, Integer age, Integer departmentId, LocalDateTime updatedAt) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.age = age;
        this.departmentId = departmentId;
        this.updatedAt = updatedAt;
    }
}