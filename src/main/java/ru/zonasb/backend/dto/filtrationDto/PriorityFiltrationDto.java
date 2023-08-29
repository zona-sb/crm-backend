package ru.zonasb.backend.dto.filtrationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityFiltrationDto {
    private String title;
    private String color;
    private Integer weight;
}
