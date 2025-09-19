package ru.java.ukhanov.t1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.ukhanov.t1.dto.AccountDto;
import ru.java.ukhanov.t1.dto.ClientDto;
import ru.java.ukhanov.t1.dto.ClientsPageDto;
import ru.java.ukhanov.t1.model.Account;
import ru.java.ukhanov.t1.model.Client;
import ru.java.ukhanov.t1.model.kafka.AccountMessage;
import ru.java.ukhanov.t1.model.kafka.ClientAccountMessage;
import ru.java.ukhanov.t1.repository.AccountRepository;
import ru.java.ukhanov.t1.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientAccountService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void processClientAccountMessage(ClientAccountMessage message) {
        log.info("Processing client account message for client: {}", message.getClientId());

        try {
            Client client = new Client(
                    message.getClientId(),
                    message.getClientName(),
                    message.getAge(),
                    message.getDepartmentId(),
                    message.getUpdatedAt()
            );
            clientRepository.save(client);

            accountRepository.deleteByClientId(message.getClientId());

            if (message.getAccounts() != null) {
                for (AccountMessage accountMsg : message.getAccounts()) {
                    Account account = new Account(
                            message.getClientId(),
                            accountMsg.getNumber(),
                            accountMsg.getOpeningDate(),
                            accountMsg.getClosingDate(),
                            accountMsg.getBalance()
                    );
                    accountRepository.save(account);
                }
            }

            log.info("Successfully processed client account message for client: {}", message.getClientId());
        } catch (Exception e) {
            log.error("Error processing client account message for client: {}", message.getClientId(), e);
            throw e;
        }
    }

    public ClientsPageDto getClients(int page, int size, String clientName, Integer minAge, Integer maxAge) {
        log.info("Getting clients page: {}, size: {}", page, size);

        List<ClientDto> clients = clientRepository.findClientsWithPagination(page, size, clientName, minAge, maxAge);
        long totalElements = clientRepository.countClients(clientName, minAge, maxAge);

        int totalPages = (int) Math.ceil((double) totalElements / size);

        return new ClientsPageDto(clients, page, size, totalElements, totalPages);
    }

    public List<AccountDto> getAccountsByClientId(String clientId, BigDecimal minAmount) {
        log.info("Getting accounts for client: {}", clientId);

        return accountRepository.findAccountsByClientId(clientId, minAmount);
    }
}