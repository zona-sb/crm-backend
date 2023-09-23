package ru.zonasb.backend.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.model.tasks.QPriority;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long>,
        QuerydslPredicateExecutor<Priority>,
        QuerydslBinderCustomizer<QPriority> {
    Optional<Priority> findPriorityByTitle(String title);
    Optional<Priority> findPriorityByWeight(int priorityWeight);
    Optional<Priority> findPriorityByColor(String color);

    Page<Priority> findAll(Predicate predicate, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QPriority priority) {

        bindings.bind(priority.title, priority.color)
                .first(StringExpression::containsIgnoreCase);

        bindings.bind(priority.weight).first(SimpleExpression::eq);
    }
}
