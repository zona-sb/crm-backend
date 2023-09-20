package ru.zonasb.backend.dto.task;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    @NotBlank(message = "Title should not be Empty")
    @Size(max = 255)
    private String statusTitle;

    @NotNull(message = "Category id cannot be null")
    @Min(value = 1, message = "Category id must be a positive integer")
    @Positive
    @Digits(integer = 10, fraction = 0)
    private Long categoryId;
}
