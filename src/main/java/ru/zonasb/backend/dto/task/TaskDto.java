package ru.zonasb.backend.dto.task;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    @NotBlank(message = "Address should not be empty")
    private String address;

    @NotNull(message = "Date should not be Empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private Date date;

    private Integer operationNumber;

    private String comment;

    @NotNull
    private Boolean completed;

    private Long statusId;

    private Long categoryId;

    private Long priorityId;

    private Long managerId;

    private Long clientId;

}
