package ru.zonasb.backend.service.task.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.CategoryRepository;
import ru.zonasb.backend.repository.StatusRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.task.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    TaskRepository taskRepository;
    StatusRepository statusRepository;

    @Override
    public Category createNewCategory(final CategoryDto categoryDto) {

        if (categoryRepository.findCategoryByCategoryTitle(categoryDto.getCategoryTitle()).isPresent()) {
            throw new IllegalArgumentException("Category with this title is already exist");
        }

        Category categoryToSave = new Category();
        categoryToSave.setCategoryTitle(categoryDto.getCategoryTitle());
        return categoryRepository.save(categoryToSave);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(final Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with this id is not found"));
    }

    @Override
    public Category updateCategory(final Long id, final CategoryDto categoryDto) {
        Category categoryToUpdate = getCategoryById(id);
        categoryToUpdate.setCategoryTitle(categoryDto.getCategoryTitle());
        return categoryRepository.save(categoryToUpdate);
    }


    @Override
    public void deleteCategoryById(final Long id) {

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByCategory(getCategoryById(id));
        optionalTasks.ifPresent(tasks -> {
            taskRepository.deleteAll(tasks);
        });

        Optional<List<Status>> optionalStatuses = statusRepository.findStatusByCategory(getCategoryById(id));
        optionalStatuses.ifPresent(statuses -> {
            statusRepository.deleteAll(statuses);
        });

        categoryRepository.deleteById(id);
    }
}
