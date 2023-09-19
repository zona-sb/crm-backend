package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.tasks.Category;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.zonasb.backend.model.tasks.QCategory;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category>,
        QuerydslBinderCustomizer<QCategory> {
    Optional<Category> findCategoryByCategoryTitle(String categoryTitle);

    @Override
    default void customize(QuerydslBindings bindings, QCategory category) {

        bindings.bind(category.categoryTitle)
                .first((stringPath, str) -> stringPath.containsIgnoreCase(str));
    }
}
