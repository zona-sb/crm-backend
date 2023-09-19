package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.tasks.Task;

import java.util.List;

public interface TaskService {
    Task createNewTask(TaskDto taskDto);
    List<Task> getActiveTasks(Long categoryId);
    List<Task> getArchiveTasks(Long categoryId);
    Task getTaskById(Long id);
    Task updateTask(Long id, TaskDto taskDto);
    void deleteTask(DeleteDto deleteDto);
}
