package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;

import java.util.List;

public interface PriorityService {

    Priority createNewPriority(PriorityDto priorityDto);

    List<Priority> getAllPriorities();

    Priority getPriorityById(Long id);

    Priority updatePriority(Long id, PriorityDto priorityDto);

    void deletePriorityById(Long id);

    void bulkDeletePriority(List<Long> ids);
}
