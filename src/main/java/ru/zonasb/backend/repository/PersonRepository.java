package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.people.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
