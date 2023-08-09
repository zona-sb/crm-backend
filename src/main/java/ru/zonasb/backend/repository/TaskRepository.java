package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.model.tasks.Status;
import ru.zonasb.backend.model.tasks.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findTaskByCategory(Category category);

    Optional<List<Task>> findTaskByPriority(Priority priority);

    Optional<List<Task>> findTaskByCategoryAndStatus(Category category, Status status);

    List<Task> findAllByOrderByDateDesc();

    Optional<Task> findTaskByOperationNumber(Integer operationNumber);
}
