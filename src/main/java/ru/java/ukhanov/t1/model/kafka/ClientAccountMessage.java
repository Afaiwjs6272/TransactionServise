package ru.java.ukhanov.t1.model.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientAccountMessage {
    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("name")
    private String clientName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("departmentId")
    private Integer departmentId;

    @JsonProperty("accounts")
    private List<AccountMessage> accounts;
}