package ru.java.ukhanov.t1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.java.ukhanov.t1.model.kafka.ClientAccountMessage;
import ru.java.ukhanov.t1.service.ClientAccountService;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientAccountConsumer {

    private final ClientAccountService clientAccountService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "client-accounts", groupId = "client-accounts-service")
    public void consume(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received message from topic: {}, partition: {}, offset: {}", topic, partition, offset);

        try {
            ClientAccountMessage clientAccountMessage = objectMapper.readValue(message, ClientAccountMessage.class);
            clientAccountService.processClientAccountMessage(clientAccountMessage);
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", message, e);
        }
    }
}