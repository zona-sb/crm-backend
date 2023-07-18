package ru.zonasb.backend.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String address;

    private Date date;

    private Integer operationNumber;

    private String comment;

    private Boolean completed;

    private Long statusId;

    private Long categoryId;

    private Long priorityId;

    private Long managerId;

    private Long clientId;

}
