package ru.zonasb.backend.service.task.interfase;

import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.model.tasks.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    Task createNewTask(TaskDto taskDto);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, TaskDto taskDto);

    Task patchUpdateTask(Long id, Map<String, Object> update);

    void deleteTaskById(Long id);

    void bulkDeleteTask(List<Long> ids);

}
