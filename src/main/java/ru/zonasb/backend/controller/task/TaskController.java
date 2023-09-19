package ru.zonasb.backend.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.service.task.interfase.TaskService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + TaskController.TASK_CONTROLLER_PATH)
public class TaskController {

    public static final String TASK_CONTROLLER_PATH = "/tasks";
    public static final String ACTIVE_TASK_CONTROLLER_PATH="/active";
    public static final String ARCHIVE_TASK_CONTROLLER_PATH="/archive";
    public static final String ID = "/{id}";
    private final TaskService taskService;


    @Operation(summary = "Get task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task with this id found"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @GetMapping(ID)
    public Task getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Get all active tasks")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @GetMapping(ACTIVE_TASK_CONTROLLER_PATH+ID)
    public List<Task> getActiveTasks(@PathVariable long id) {
        return taskService.getActiveTasks(id);
    }

    @Operation(summary = "Get all archive tasks")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @GetMapping(ARCHIVE_TASK_CONTROLLER_PATH+ID)
    public List<Task> getArchiveTasks(@PathVariable long id) {
        return taskService.getArchiveTasks(id);
    }

    @Operation(summary = "Create new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createNewTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.createNewTask(taskDto);
    }

    @Operation(summary = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @PutMapping(ID)
    public Task updateTask(@PathVariable long id,  @RequestBody @Valid TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }


    @Operation(summary = "Delete tasks by id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Some tasks were deleted"),
            @ApiResponse(responseCode = "400", description = "The given id list contained invalid values")
    })
    @DeleteMapping()
    public void deleteTasks(@RequestBody DeleteDto deleteDto) {
        taskService.deleteTask(deleteDto);
    }


}
