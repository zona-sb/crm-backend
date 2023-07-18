package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.Status;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByCategoryTitle(String categoryTitle);

    Optional<Category> findCategoryByStatus(Status status);
}
