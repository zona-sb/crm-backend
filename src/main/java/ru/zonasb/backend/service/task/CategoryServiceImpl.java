package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.CategoryRepository;
import ru.zonasb.backend.repository.StatusRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.task.interfase.CategoryService;

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
                .orElseThrow(() -> new NoSuchElementException("Category with this id " + id + " is not found"));
    }

    @Override
    public Category updateCategory(final Long id, final CategoryDto categoryDto) {
        if (categoryRepository.findCategoryByCategoryTitle(categoryDto.getCategoryTitle()).isPresent()) {
            throw new IllegalArgumentException("Category with this title is already exist");
        }

        Category categoryToUpdate = getCategoryById(id);
        categoryToUpdate.setCategoryTitle(categoryDto.getCategoryTitle());
        return categoryRepository.save(categoryToUpdate);
    }


    public void deleteCategoryById(final Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid category id");
        }

        String idString = String.valueOf(id);
        if (idString.contains(" ")) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Invalid category id");
        }

        Category category = getCategoryById(id);

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByCategory(category);
        optionalTasks.ifPresent(tasks -> taskRepository.deleteAll(tasks));

        Optional<List<Status>> optionalStatuses = statusRepository.findStatusByCategory(category);
        optionalStatuses.ifPresent(statuses -> statusRepository.deleteAll(statuses));

        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteCategory(DeleteDto deleteDto) {
        if (deleteDto.isDeleteAll()) {
            categoryRepository.deleteAll();
        } else {
            deleteDto.getIds().forEach(this::deleteCategoryById);
        }
    }
}
