package ru.zonasb.backend.service.task;

import lombok.*;
import org.springframework.stereotype.*;
import ru.zonasb.backend.dto.task.*;
import ru.zonasb.backend.model.tasks.*;
import ru.zonasb.backend.repository.*;
import ru.zonasb.backend.service.task.interfase.*;

import java.util.*;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    StatusRepository statusRepository;
    CategoryService categoryService;
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
                .orElseThrow(() -> new NoSuchElementException("Status with id " + id + " is not found"));
    }

    @Override
    public Status updateStatus(final Long id, final StatusDto statusDto) {
        Category category = categoryService.getCategoryById(statusDto.getCategoryId());
        String statusTitle =  statusDto.getStatusTitle();

        if (statusRepository.findStatusByCategoryAndStatusTitle(category, statusTitle).isPresent()) {
            throw new IllegalArgumentException("Status title should be changed in order to update status");
        }
        Status statusToUpdate = getStatusById(id);
        statusToUpdate.setStatusTitle(statusTitle);
        statusToUpdate.setCategory(category);
        return statusRepository.save(statusToUpdate);
    }

    @Override
    public void deleteStatusById(final Long id) {
        Status status = getStatusById(id);
        Category category = status.getCategory();

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByCategoryAndStatus(category, status);
        optionalTasks.ifPresent(tasks -> taskRepository.deleteAll(tasks));

        statusRepository.deleteById(id);
    }

    @Override
    public void bulkDeleteStatus(List<Long> ids) {
        ids.forEach(this::deleteStatusById);
    }
}
