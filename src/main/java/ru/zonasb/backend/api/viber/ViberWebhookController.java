package ru.zonasb.backend.api.viber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ViberWebhookController {

    @PostMapping("/webhook")
    public String handleWebhook(@RequestBody String payload) {
        WebhookResponse webhookResponse = WebhookResponse.builder()
                .status(0)
                .status_message("ok")
                .event_types(new ArrayList<>())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        // Здесь вы можете обработать входящий запрос от Viber API
        try {
            return objectMapper.writeValueAsString(webhookResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
