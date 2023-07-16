package ru.zonasb.backend.api.viber;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViberWebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        // Здесь вы можете обработать входящий запрос от Viber API
        return ResponseEntity.ok("Webhook received");
    }
}
