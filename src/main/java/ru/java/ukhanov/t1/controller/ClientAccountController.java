package ru.java.ukhanov.t1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.ukhanov.t1.dto.AccountDto;
import ru.java.ukhanov.t1.dto.ClientsPageDto;
import ru.java.ukhanov.t1.service.ClientAccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClientAccountController {

    private final ClientAccountService clientAccountService;

    @GetMapping("/clients")
    public ResponseEntity<ClientsPageDto> getClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {

        log.info("Processing GET /clients - page: {}, size: {}, clientName: {}, minAge: {}, maxAge: {}",
                page, size, clientName, minAge, maxAge);

        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }

        ClientsPageDto result = clientAccountService.getClients(page, size, clientName, minAge, maxAge);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/accounts/{clientId}")
    public ResponseEntity<List<AccountDto>> getAccountsByClientId(
            @PathVariable String clientId,
            @RequestParam(required = false) BigDecimal minAmount) {

        log.info("Processing GET /accounts/{} - minAmount: {}", clientId, minAmount);

        List<AccountDto> accounts = clientAccountService.getAccountsByClientId(clientId, minAmount);
        return ResponseEntity.ok(accounts);
    }
}