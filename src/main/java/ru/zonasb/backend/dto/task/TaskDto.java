package ru.zonasb.backend.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    @NotBlank(message = "Address should not be empty")
    private String address;

    @NotNull(message = "Date should not be Empty")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    private Integer operationNumber;

    private String comment;

    @NotNull
    private Boolean completed;

    private Long statusId;

    private Long categoryId;

    private Long priorityId;

    @NotNull
    private Long clientId;

}
