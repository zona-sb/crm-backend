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
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.*;
import ru.zonasb.backend.service.task.interfase.CategoryService;


@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + CategoryController.CATEGORY_CONTROLLER_PATH)
public class CategoryController {

    public static final String CATEGORY_CONTROLLER_PATH = "/categories";
    public static final String ID = "/{id}";
    private final CategoryService categoryService;


    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Category with this id does not exist")
    })
    @GetMapping(ID)
    public Category getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Categories were successfully found")
    @GetMapping
    public Page<Category> getAllCategories(
            @QuerydslPredicate(root = Category.class) Predicate predicate,
            @SortDefault(sort="categoryTitle", direction= Sort.Direction.ASC) Pageable pageable) {
        return categoryService.getAllCategories(predicate, pageable);
    }

    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createNewCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.createNewCategory(categoryDto);
    }

    @Operation(summary = "Update category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Category with this id does not exist")
    })
    @PutMapping(ID)
    public Category updateCategory(@PathVariable long id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @Operation(summary = "Delete categories by id list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Some categories were deleted"),
            @ApiResponse(responseCode = "400", description = "The given id list contained invalid values")
    })
    @DeleteMapping()
    public void deleteCategories(@RequestBody DeleteDto deleteDto) {
        categoryService.deleteCategory(deleteDto);
    }
}
