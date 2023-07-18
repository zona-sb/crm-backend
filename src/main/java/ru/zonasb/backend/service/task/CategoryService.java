package ru.zonasb.backend.service.task;

import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;

import java.util.List;

public interface CategoryService {
    Category createNewCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategoryById(Long id);
}
