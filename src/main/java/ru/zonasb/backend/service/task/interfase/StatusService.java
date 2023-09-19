package ru.zonasb.backend.service.task.interfase;

import com.querydsl.core.types.*;
import ru.zonasb.backend.dto.*;
import ru.zonasb.backend.dto.task.*;
import ru.zonasb.backend.model.tasks.*;

public interface StatusService {
    Status createNewStatus(StatusDto statusDto);

    public Iterable<Status> getAll(Predicate p1, Predicate p2);

    Status getStatusById(Long id);

    Status updateStatus(Long id, StatusDto statusDto);

    void deleteStatus(DeleteDto deleteDto);

}
