package ru.zonasb.backend.service.worker;

import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Worker;

public interface WorkerService {
    Worker getWorkerById(long id);
    WorkerDto createNewWorker(WorkerDto workerDto);
    WorkerDto updateWorkerById(long id, WorkerDto workerDto);


}
