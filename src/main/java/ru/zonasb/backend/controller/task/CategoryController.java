package ru.zonasb.backend.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.service.task.interfase.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + CategoryController.CATEGORY_CONTROLLER_PATH)
public class CategoryController {

    public static final String CATEGORY_CONTROLLER_PATH = "/categories";
    public static final String ID = "/{id}";

    private final CategoryService categoryService;


    @Operation(summary = "Get Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Category with this id does not exist")
    })
    @GetMapping(ID)
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Get all Categories")
    @ApiResponse(responseCode = "200", description = "Categories were successfully found")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @Operation(summary = "Create new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category was successfully created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createNewCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createNewCategory(categoryDto);
    }


    @Operation(summary = "Update Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category with this id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Category with this id does not exist")
    })
    @PutMapping(ID)
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }


    @Operation(summary = "Delete Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category with this id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category with this id does not exist")
    })
    @DeleteMapping(ID)
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
