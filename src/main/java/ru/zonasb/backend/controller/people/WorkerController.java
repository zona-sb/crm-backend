package ru.zonasb.backend.controller.people;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Worker;
import ru.zonasb.backend.service.people.interfase.WorkerService;

import java.util.List;

import static ru.zonasb.backend.controller.people.WorkerController.WORKER_CONTROLLER_PATH;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + WORKER_CONTROLLER_PATH)
public class WorkerController {

    public static final String WORKER_CONTROLLER_PATH = "/workers";
    private static final String MYSELF = "/myself";
    public static final String ID = "/{id}";
    private final WorkerService workerService;

    @GetMapping(ID)
    public Worker getWorkerById(@PathVariable long id) {
        return workerService.getWorkerById(id);
    }

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Worker createNewWorker(@RequestBody @Valid WorkerDto workerDto) {
        return workerService.createNewWorker(workerDto);
    }

    @PutMapping(ID)
    public Worker updateWorkerById(@PathVariable long id, @RequestBody @Valid WorkerDto workerDto) {
        return workerService.updateWorkerById(id, workerDto);
    }

    @PostMapping(MYSELF)
    public Worker createMyselfWorker() {
        return workerService.createMySelfAsWorker();
    }

    @DeleteMapping(ID)
    public void deleteWorkerById(@PathVariable long id) {
        workerService.deleteWorkerById(id);
    }
}