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
        if (!taskToUpdate.getOperationNumber().equals(taskDto.getOperationNumber())
                && taskRepository.findTaskByOperationNumber(taskDto.getOperationNumber()).isPresent()) {
            throw new IllegalArgumentException("Task with this operation number is already exist");
        }
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
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public void bulkDeleteTask(List<Long> ids) {
        ids.forEach(this::deleteTaskById);
    }

}
