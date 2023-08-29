package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;

import com.querydsl.core.types.Predicate;

public interface PriorityService {

    Priority createNewPriority(PriorityDto priorityDto);

    Iterable<Priority> getAllPriorities(Predicate predicate);

    Priority getPriorityById(Long id);

    Priority updatePriority(Long id, PriorityDto priorityDto);

    void deletePriority(DeleteDto deleteDto);
}
