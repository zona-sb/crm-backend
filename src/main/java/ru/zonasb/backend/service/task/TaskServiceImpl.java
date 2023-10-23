package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.people.Manager;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.people.interfase.ClientService;
import ru.zonasb.backend.service.people.interfase.ManagerService;
import ru.zonasb.backend.service.people.interfase.UserService;
import ru.zonasb.backend.service.task.interfase.CategoryService;
import ru.zonasb.backend.service.task.interfase.PriorityService;
import ru.zonasb.backend.service.task.interfase.StatusService;
import ru.zonasb.backend.service.task.interfase.TaskService;

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
    UserService userService;


    @Override
    public Task createNewTask(final TaskDto taskDto) {
        Manager manager;
//        if (userService.getCurrentUser() == null) {
//            manager = managerService.getManagerById(1);
//        } else {
//            manager = managerService.getManagerByUserId(userService.getCurrentUser().getId());
//        }
        try {
            manager = managerService.getManagerByUserId(userService.getCurrentUser().getId());
        } catch (Exception e) {
            manager = managerService.getManagerById(1);
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
                .manager(manager)
                .client(clientService.getClientById(taskDto.getClientId()))
                .build();

        return taskRepository.save(task);

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
        taskToUpdate.setClient(clientService.getClientById(taskDto.getClientId()));

        return taskRepository.save(taskToUpdate);
    }

    @Override
    public List<Task> getActiveTasks(final Long categoryId) {
        return taskRepository.findTasksByCompletedFalseAndCategoryIdOrderByDateDesc(categoryId);
    }

    @Override
    public List<Task> getArchiveTasks(final Long categoryId) {
        return taskRepository.findTasksByCompletedTrueAndCategoryIdOrderByPriorityDesc(categoryId);
    }

    @Override
    public Task getTaskById(final Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " is not found"));
    }

    @Override
    public void deleteTask(DeleteDto deleteDto) {
        if (deleteDto.isDeleteAll()) {
            taskRepository.deleteAll();
        } else {
            taskRepository.deleteAllById(deleteDto.getIds());
        }
    }

}
