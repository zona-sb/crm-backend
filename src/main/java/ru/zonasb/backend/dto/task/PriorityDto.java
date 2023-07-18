package ru.zonasb.backend.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {
    private String title;
    private String color;
    private int weight;
}
