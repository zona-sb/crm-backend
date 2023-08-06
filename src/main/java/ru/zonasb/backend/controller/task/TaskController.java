package ru.zonasb.backend.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.service.task.interfase.TaskService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + TaskController.TASK_CONTROLLER_PATH)
public class TaskController {

    public static final String TASK_CONTROLLER_PATH = "/tasks";
    public static final String ID = "/{id}";

    private final TaskService taskService;


    @Operation(summary = "Get Task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @GetMapping(ID)
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Get all Categories")
    @ApiResponse(responseCode = "200", description = "Tasks were successfully found")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


    @Operation(summary = "Create new Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createNewTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.createNewTask(taskDto);
    }


    @Operation(summary = "Update Task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @PutMapping(ID)
    public Task updateTask(@PathVariable Long id,  @RequestBody @Valid TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @Operation(summary = "Partial update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @PatchMapping(ID)
    public Task patchUpdateTask(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        return taskService.patchUpdateTask(id, update);
    }


    @Operation(summary = "Delete Task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task with this id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Task with this id does not exist")
    })
    @DeleteMapping(ID)
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
    }

    @Operation(summary = "Delete multiple tasks by id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Some tasks were deleted"),
            @ApiResponse(responseCode = "400", description = "The given id list contained invalid values")
    })
    @DeleteMapping("/bulk")
    public void bulkDeleteTaskByIds(@RequestBody List<Long> ids) {
        taskService.bulkDeleteTask(ids);
    }

}
