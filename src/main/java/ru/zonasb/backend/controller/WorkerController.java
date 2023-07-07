package ru.zonasb.backend.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Worker;
import ru.zonasb.backend.service.worker.WorkerService;

import static ru.zonasb.backend.controller.WorkerController.WORKER_CONTROLLER_PATH;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + WORKER_CONTROLLER_PATH)
public class WorkerController {

    public static final String WORKER_CONTROLLER_PATH = "/workers";
    public static final String ID = "/{id}";
    private final WorkerService workerService;

    @GetMapping(ID)
    public Worker getWorkerById(@PathVariable long id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerDto createNewWorker(@RequestBody WorkerDto workerDto) {
        return workerService.createNewWorker(workerDto);
    }

    @PostMapping(ID)
    public WorkerDto updateWorkerById(@PathVariable long id, @RequestBody WorkerDto workerDto) {
        return workerService.updateWorkerById(id, workerDto);
    }

}
