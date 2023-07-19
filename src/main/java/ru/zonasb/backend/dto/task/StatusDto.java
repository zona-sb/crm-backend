package ru.zonasb.backend.dto.task;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    @NotBlank(message = "Title should not be Empty")
    @Size(max = 255)
    private String statusTitle;

    private Long categoryId;
}
