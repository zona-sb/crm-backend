package ru.zonasb.backend.service.task.interfase;

import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import ru.zonasb.backend.dto.*;
import ru.zonasb.backend.dto.task.*;
import ru.zonasb.backend.model.tasks.*;

public interface StatusService {
    Status createNewStatus(StatusDto statusDto);

    Page<Status> getAll(Predicate p1, Predicate p2, Pageable pageable);

    Status getStatusById(Long id);

    Status updateStatus(Long id, StatusDto statusDto);

    void deleteStatus(DeleteDto deleteDto);

}
