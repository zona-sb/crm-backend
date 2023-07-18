package ru.zonasb.backend.service.task.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.CategoryRepository;
import ru.zonasb.backend.repository.StatusRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.task.CategoryService;
import ru.zonasb.backend.service.task.StatusService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    StatusRepository statusRepository;
    CategoryService categoryService;
    CategoryRepository categoryRepository;
    TaskRepository taskRepository;

    @Override
    public Status createNewStatus(final StatusDto statusDto) {
        Category category = categoryService.getCategoryById(statusDto.getCategoryId());
        String statusTitle =  statusDto.getStatusTitle();

        if (statusRepository.findStatusByCategoryAndStatusTitle(category, statusTitle).isPresent()) {
            throw new IllegalArgumentException("Status with this title is already exist in this category");
        }

        Status status = Status.builder()
                .statusTitle(statusTitle)
                .category(category)
                .build();

        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(final Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Status with this id is not found"));
    }

    @Override
    public Status updateStatus(final Long id, final StatusDto statusDto) {
        Status statusToUpdate = getStatusById(id);
        statusToUpdate.setStatusTitle(statusDto.getStatusTitle());
        statusToUpdate.setCategory(categoryService.getCategoryById(statusDto.getCategoryId()));
        return statusRepository.save(statusToUpdate);
    }


    @Override
    public void deleteStatusById(final Long id) {
        Status status = getStatusById(id);
        Category category = status.getCategory();
        Optional<List<Task>> optionalTasks = taskRepository.findTaskByCategoryAndStatus(category, status);
        optionalTasks.ifPresent(tasks -> {
            taskRepository.deleteAll(tasks);
        });
        statusRepository.deleteById(id);
    }
}
