package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.model.tasks.Status;

import java.util.List;

public interface StatusService {
    Status createNewStatus(StatusDto statusDto);

    List<Status> getAllStatuses();

    Status getStatusById(Long id);

    Status updateStatus(Long id, StatusDto statusDto);

    void deleteStatusById(Long id);
}
