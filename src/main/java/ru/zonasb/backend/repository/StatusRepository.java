package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findStatusByCategoryAndStatusTitle(Category category,String statusTitle);
    Optional<List<Status>> findStatusByCategory(Category category);

}
