package ru.zonasb.backend.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
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
