package ru.zonasb.backend.dto.people;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    @NotBlank(message = "name should not be Empty")
    private String name;

    @NotBlank(message = "phone should not be Empty")
    @Size(min = 12, max = 12)
    @Pattern(regexp = "^\\+7\\d{10}$", message = "phone number should be like \"+7**********\"")
    private String phone;

    @NotBlank(message = "email should not be Empty")
    @Column(name = "email", unique = true)
    private String email;
}
