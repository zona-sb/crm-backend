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
import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.service.task.interfase.StatusService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + StatusController.STATUS_CONTROLLER_PATH)
public class StatusController {

    public static final String STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/{id}";

    private final StatusService statusService;


    @Operation(summary = "Get Status by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Status with this id does not exist")
    })
    @GetMapping(ID)
    public Status getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @Operation(summary = "Get all Categories")
    @ApiResponse(responseCode = "200", description = "Statuses were successfully found")
    @GetMapping
    public List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }


    @Operation(summary = "Create new Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Status createNewStatus(@RequestBody @Valid StatusDto statusDto) {
        return statusService.createNewStatus(statusDto);
    }


    @Operation(summary = "Update Status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Status with this id does not exist")
    })
    @PutMapping(ID)
    public Status updateStatus(@PathVariable Long id, @RequestBody @Valid StatusDto statusDto) {
        return statusService.updateStatus(id, statusDto);
    }


    @Operation(summary = "Delete Status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status with this id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Status with this id does not exist")
    })
    @DeleteMapping(ID)
    public void deleteStatusById(@PathVariable Long id) {
        statusService.deleteStatusById(id);
    }

}


