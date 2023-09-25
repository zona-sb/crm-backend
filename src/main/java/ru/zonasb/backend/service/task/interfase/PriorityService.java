package ru.zonasb.backend.service.task.interfase;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;


public interface PriorityService {

    Priority createNewPriority(PriorityDto priorityDto);

    Page<Priority> getAllPriorities(Predicate predicate, Pageable pageable);

    Priority getPriorityById(Long id);

    Priority updatePriority(Long id, PriorityDto priorityDto);

    void deletePriority(DeleteDto deleteDto);
}
