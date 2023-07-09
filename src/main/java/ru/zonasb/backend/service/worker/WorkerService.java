package ru.zonasb.backend.service.worker;

import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Worker;

import java.util.List;

public interface WorkerService {
    Worker getWorkerById(long id);
    List<Worker> getAllWorkers();
    Worker createNewWorker(WorkerDto workerDto);
    Worker createMySelfAsWorker();
    Worker updateWorkerById(long id, WorkerDto workerDto);


}
