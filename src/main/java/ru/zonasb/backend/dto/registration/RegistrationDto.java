package ru.zonasb.backend.dto.registration;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    @NotBlank(message = "name should not be Empty")
    private String name;

    @NotBlank(message = "phone should not be Empty")
    @Size(min = 12, max = 12)
    @Pattern(regexp = "^\\+7\\d{10}$", message = "phone number should be like \"+7**********\"")
    private String phone;

    @NotBlank(message = "email should not be Empty")
    @Email(message = "Incorrect Email")
    private String email;

    @NotBlank(message = "password should not be Empty")
    @Size(min = 3, max = 100, message = "Password should be between at 3 to 100 symbols")
    private String password;
}
