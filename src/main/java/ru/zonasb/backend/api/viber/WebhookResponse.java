package ru.zonasb.backend.api.viber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebhookResponse {
    private int status;
    private String status_message;
    private List<String> event_types;
}
