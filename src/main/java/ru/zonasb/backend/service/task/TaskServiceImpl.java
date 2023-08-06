package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.people.interfase.ClientService;
import ru.zonasb.backend.service.people.interfase.ManagerService;
import ru.zonasb.backend.service.task.interfase.CategoryService;
import ru.zonasb.backend.service.task.interfase.PriorityService;
import ru.zonasb.backend.service.task.interfase.StatusService;
import ru.zonasb.backend.service.task.interfase.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    StatusService statusService;
    CategoryService categoryService;
    PriorityService priorityService;
    ManagerService managerService;
    ClientService clientService;

    @Override
    public Task createNewTask(final TaskDto taskDto) {

        LocalDate today = LocalDate.now();

        if (taskDto.getDate().isBefore(today)) {
            throw new IllegalArgumentException("The date must be no earlier than today");
        }

        Task task = Task.builder()
                .address(taskDto.getAddress())
                .date(taskDto.getDate())
                .operationNumber(taskDto.getOperationNumber())
                .comment(taskDto.getComment())
                .completed(taskDto.getCompleted())
                .status(statusService.getStatusById(taskDto.getStatusId()))
                .category(categoryService.getCategoryById(taskDto.getCategoryId()))
                .priority(priorityService.getPriorityById(taskDto.getPriorityId()))
                .manager(managerService.getManagerById(taskDto.getManagerId()))
                .client(clientService.getClientById(taskDto.getClientId()))
                .build();

        return taskRepository.save(task);

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Task getTaskById(final Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " is not found"));
    }


    @Override
    public Task updateTask(final Long id, final TaskDto taskDto) {

        LocalDate today = LocalDate.now();

        if (taskDto.getDate().isBefore(today)) {
            throw new IllegalArgumentException("The date must be no earlier than today");
        }

        Task taskToUpdate = getTaskById(id);

        taskToUpdate.setAddress(taskDto.getAddress());
        taskToUpdate.setDate(taskDto.getDate());
        taskToUpdate.setOperationNumber(taskDto.getOperationNumber());
        taskToUpdate.setComment(taskDto.getComment());
        taskToUpdate.setCompleted(taskDto.getCompleted());
        taskToUpdate.setStatus(statusService.getStatusById(taskDto.getStatusId()));
        taskToUpdate.setCategory(categoryService.getCategoryById(taskDto.getCategoryId()));
        taskToUpdate.setPriority(priorityService.getPriorityById(taskDto.getPriorityId()));
        taskToUpdate.setManager(managerService.getManagerById(taskDto.getManagerId()));
        taskToUpdate.setClient(clientService.getClientById(taskDto.getClientId()));

        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Task patchUpdateTask(Long id, Map<String, Object> update) {

        Task taskToUpdate = getTaskById(id);

        if (update.containsKey("address")) {
            taskToUpdate.setAddress((String) update.get("address"));
        }

        if (update.containsKey("date")) {
            LocalDate today = LocalDate.now();
            LocalDate date = (LocalDate) update.get("date");

            if (date.isBefore(today)) {
                throw new IllegalArgumentException("The date must be no earlier than today");
            }

            taskToUpdate.setDate((LocalDate) update.get("date"));
        }

        if (update.containsKey("operationNumber")) {
            taskToUpdate.setOperationNumber((Integer) update.get("operationNumber"));
        }

        if (update.containsKey("comment")) {
            taskToUpdate.setComment((String) update.get("comment"));
        }

        if (update.containsKey("completed")) {
            taskToUpdate.setCompleted((Boolean) update.get("completed"));
        }

        if (update.containsKey("statusId")) {
            Long statusId = (Long) update.get("statusId");
            taskToUpdate.setStatus(statusService.getStatusById(statusId));
        }


        if (update.containsKey("categoryId")) {
            Long categoryId = (Long) update.get("categoryId");
            taskToUpdate.setCategory(categoryService.getCategoryById(categoryId));
        }


        if (update.containsKey("priorityId")) {
            Long priorityId = (Long) update.get("priorityId");
            taskToUpdate.setPriority(priorityService.getPriorityById(priorityId));
        }


        if (update.containsKey("managerId")) {
            Long managerId = (Long) update.get("managerId");
            taskToUpdate.setManager(managerService.getManagerById(managerId));
        }


        if (update.containsKey("clientId")) {
            Long clientId = (Long) update.get("clientId");
            taskToUpdate.setClient(clientService.getClientById(clientId));
        }

        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteTaskById(final Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public void bulkDeleteTask(List<Long> ids) {
        ids.forEach(this::deleteTaskById);
    }

}
