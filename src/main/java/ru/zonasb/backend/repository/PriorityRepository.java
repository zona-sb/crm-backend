package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Priority;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findPriorityByTitle(String title);
    Optional<Priority> findPriorityByWeight(int priorityWeight);
    Optional<Priority> findPriorityByColor(String color);
}
