package ru.zonasb.backend.service.task;

import lombok.*;
import org.springframework.stereotype.*;
import ru.zonasb.backend.dto.task.*;
import ru.zonasb.backend.model.tasks.*;
import ru.zonasb.backend.repository.*;
import ru.zonasb.backend.service.people.interfase.*;
import ru.zonasb.backend.service.task.interfase.*;

import java.util.*;

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
                .orElseThrow(() -> new NoSuchElementException("Task with this id is not found"));
    }


    @Override
    public Task updateTask(final Long id, final TaskDto taskDto) {
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
    public void deleteTaskById(final Long id) {
        taskRepository.deleteById(id);
    }
}
