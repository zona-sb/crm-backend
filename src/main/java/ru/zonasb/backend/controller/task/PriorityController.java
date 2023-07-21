package ru.zonasb.backend.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.service.task.interfase.PriorityService;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + PriorityController.PRIORITY_CONTROLLER_PATH)
public class PriorityController {

    public static final String PRIORITY_CONTROLLER_PATH = "/priorities";
    public static final String ID = "/{id}";

    private final PriorityService priorityService;


    @Operation(summary = "Get Priority by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @GetMapping(ID)
    public Priority getPriorityById(@PathVariable Long id) {
        return priorityService.getPriorityById(id);
    }

    @Operation(summary = "Get all Categories")
    @ApiResponse(responseCode = "200", description = "Priorities were successfully found")
    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }


    @Operation(summary = "Create new Priority")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Priority was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Priority createNewPriority(@RequestBody @Valid PriorityDto priorityDto) {
        return priorityService.createNewPriority(priorityDto);
    }

    @Operation(summary = "Update Priority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @PutMapping(ID)
    public Priority updatePriority(@PathVariable Long id, @RequestBody @Valid PriorityDto priorityDto) {
        return priorityService.updatePriority(id, priorityDto);
    }


    @Operation(summary = "Delete Priority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority with this id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @DeleteMapping(ID)
    public void deletePriorityById(@PathVariable Long id) {
        priorityService.deletePriorityById(id);
    }

}
