package ru.zonasb.backend.service.task.interfase;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.*;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.*;

public interface CategoryService {
    Category createNewCategory(CategoryDto categoryDto);

    Page<Category> getAllCategories(Predicate predicate, Pageable pageable);

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(DeleteDto deleteDto);
}
