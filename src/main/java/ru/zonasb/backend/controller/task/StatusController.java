package ru.zonasb.backend.controller.task;

import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.*;
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
import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.model.tasks.*;
import ru.zonasb.backend.service.task.interfase.StatusService;



@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + StatusController.STATUS_CONTROLLER_PATH)
public class StatusController {

    public static final String STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/{id}";
    private final StatusService statusService;


    @Operation(summary = "Get status by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Status with this id does not exist")
    })
    @GetMapping(ID)
    public Status getStatusById(@PathVariable long id) {
        return statusService.getStatusById(id);
    }

    @Operation(summary = "Get all statuses")
    @ApiResponse(responseCode = "200", description = "Statuses were successfully found")
    @GetMapping
    public Page<Status> getAllStatuses(
            @QuerydslPredicate(root = Status.class) Predicate predicate,
            @QuerydslPredicate(root = Category.class) Predicate categoryPredicate,
            @SortDefault(sort="category.categoryTitle", direction= Sort.Direction.ASC) Pageable pageable) {
        return statusService.getAll(predicate, categoryPredicate, pageable);
    }

    @Operation(summary = "Create new status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Status createNewStatus(@RequestBody @Valid StatusDto statusDto) {
        return statusService.createNewStatus(statusDto);
    }


    @Operation(summary = "Update status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Status with this id does not exist")
    })
    @PutMapping(ID)
    public Status updateStatus(@PathVariable long id, @RequestBody @Valid StatusDto statusDto) {
        return statusService.updateStatus(id, statusDto);
    }


    @Operation(summary = "Delete statuses by id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Some statuses were deleted"),
            @ApiResponse(responseCode = "400", description = "The given id list contained invalid values")
    })
    @DeleteMapping()
    public void deleteStatuses(@RequestBody DeleteDto deleteDto) {
        statusService.deleteStatus(deleteDto);
    }

}


