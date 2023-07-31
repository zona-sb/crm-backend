package ru.zonasb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zonasb.backend.model.people.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByPersonEmail(String email);
    Optional<Client> findClientByPersonPhone(String phone);
}