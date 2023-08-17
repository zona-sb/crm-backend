package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;

import java.util.List;

public interface CategoryService {
    Category createNewCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(DeleteDto deleteDto);
}
