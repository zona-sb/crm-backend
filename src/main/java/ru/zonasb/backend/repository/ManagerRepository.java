package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.people.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
