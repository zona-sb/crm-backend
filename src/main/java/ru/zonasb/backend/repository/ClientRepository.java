package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.people.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}