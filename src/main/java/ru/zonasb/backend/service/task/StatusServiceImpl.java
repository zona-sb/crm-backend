package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.StatusRepository;
import ru.zonasb.backend.repository.TaskRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import ru.zonasb.backend.service.task.interfase.CategoryService;
import ru.zonasb.backend.service.task.interfase.StatusService;

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

/*
    @Override
    public Iterable<Status> getAllStatuses(Predicate predicate) {
        return statusRepository.findAll(predicate);
    }
*/

    @Override
    public Iterable<Status> getAll(Predicate p1, Predicate p2) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(p1);
        builder.and(p2);

        return statusRepository.findAll(builder);
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
    public void deleteStatus(DeleteDto deleteDto) {
        if (deleteDto.isDeleteAll()) {
            statusRepository.deleteAll();
        } else {
            deleteDto.getIds().forEach(this::deleteStatusById);
        }
    }

    public void deleteStatusById(final Long id) {

        Status status = getStatusById(id);
        Category category = status.getCategory();

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByCategoryAndStatus(category, status);
        optionalTasks.ifPresent(tasks -> taskRepository.deleteAll(tasks));

        statusRepository.deleteById(id);
    }

}
