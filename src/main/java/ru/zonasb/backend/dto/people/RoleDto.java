package ru.zonasb.backend.dto.people;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @NotBlank(message = "Role Name should not be Empty")
    private String title;
}
