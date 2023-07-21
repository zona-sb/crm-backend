package ru.zonasb.backend.dto.task;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {

    @NotBlank(message = "Priority title should not be empty")
    @Size(max = 255)
    @Pattern(regexp = "^.*$")
    private String title;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Color code must be a valid 6-digit HEX color")
    @NotBlank
    private String color;

    @NotNull(message = "Priority weight cannot be null")

    @Min(value = 1, message = "Priority weight must be a positive integer")
    @Positive
    @Digits(integer = 10, fraction = 0)
    private int weight;

}
