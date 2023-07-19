package ru.zonasb.backend.dto.task;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {

    @NotBlank(message = "Priority title should not be empty")
    @Size(max = 255)
    private String title;

    @Pattern(regexp = "^#[0-9a-fA-F]{6}$",
            message = "Color code must be a valid HEX color")
    private String color;

    @NotNull(message = "Priority weight cannot be null")
    @Min(value = 1, message = "Priority weight must be greater than 0")
    private int weight;
}
