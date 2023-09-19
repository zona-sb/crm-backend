package ru.zonasb.backend.repository;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Category;
import ru.zonasb.backend.model.tasks.QStatus;
import ru.zonasb.backend.model.tasks.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>, QuerydslPredicateExecutor<Status>,
        QuerydslBinderCustomizer<QStatus> {
    Optional<Status> findStatusByCategoryAndStatusTitle(Category category, String statusTitle);

    Optional<List<Status>> findStatusByCategory(Category category);

    @Override
    default void customize(QuerydslBindings bindings, QStatus status) {
        bindings.bind(status.statusTitle, status.category.categoryTitle)
                .first(StringExpression::containsIgnoreCase);
    }
}
