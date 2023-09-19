package ru.zonasb.backend.service.task.interfase;

import com.querydsl.core.types.Predicate;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;

public interface CategoryService {
    Category createNewCategory(CategoryDto categoryDto);

    Iterable<Category> getAllCategories(Predicate predicate);

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(DeleteDto deleteDto);
}
