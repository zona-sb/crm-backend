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
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.service.task.interfase.PriorityService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + PriorityController.PRIORITY_CONTROLLER_PATH)
public class PriorityController {

    public static final String PRIORITY_CONTROLLER_PATH = "/priorities";
    public static final String ID = "/{id}";
    private final PriorityService priorityService;

    @Operation(summary = "Get Priority by ID. Return 404 if priority not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority found"),
            @ApiResponse(responseCode = "404", description = "Priority with the given id does not exist")
    })
    @GetMapping(ID)
    public Priority getPriorityById(@PathVariable long id) {
        return priorityService.getPriorityById(id);
    }

    @Operation(summary = "Get all Categories")
    @ApiResponse(responseCode = "200", description = "Priorities found")
    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

    @Operation(summary = "Create new priority")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Priority created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Priority createNewPriority(@RequestBody @Valid PriorityDto priorityDto) {
        return priorityService.createNewPriority(priorityDto);
    }

    @Operation(summary = "Update priority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority updated"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @PutMapping(ID)
    public Priority updatePriority(@PathVariable long id, @RequestBody @Valid PriorityDto priorityDto) {
        return priorityService.updatePriority(id, priorityDto);
    }

    @Operation(summary = "Partial update priority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority updated"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @PatchMapping(ID)
    public Priority patchUpdatePriority(@PathVariable long id, @RequestBody Map<String, Object> update) {
        return priorityService.patchUpdatePriority(id, update);
    }

    @Operation(summary = "Delete priority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priority deleted"),
            @ApiResponse(responseCode = "404", description = "Priority with this id does not exist")
    })
    @DeleteMapping(ID)
    public void deletePriorityById(@PathVariable long id) {
        priorityService.deletePriorityById(id);
    }

    @Operation(summary = "Delete multiple priorities by id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Some priorities were deleted"),
            @ApiResponse(responseCode = "400", description = "The given id list contained invalid values")
    })
    @DeleteMapping("/bulk")
    public void bulkDeletePriorityByIds(@RequestBody List<Long> ids) {
        priorityService.bulkDeletePriority(ids);
    }

}