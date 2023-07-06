package ru.zonasb.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @NotBlank(message = "name should not be Empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "phone should not be Empty")
    @Column(name = "phone", unique = true)
    @Size(min = 11, max = 11, message = "phone should be 11 numbers")
    private String phone;

    @NotBlank(message = "email should not be Empty")
    @Email(message = "Incorrect Email")
    @Column(name = "email", unique = true)
    private String email;
}
