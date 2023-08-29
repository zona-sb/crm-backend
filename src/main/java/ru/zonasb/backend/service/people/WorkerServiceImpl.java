package ru.zonasb.backend.service.people;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Manager;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.model.people.User;
import ru.zonasb.backend.model.people.Worker;
import ru.zonasb.backend.repository.PersonRepository;
import ru.zonasb.backend.repository.WorkerRepository;
import ru.zonasb.backend.service.people.interfase.UserService;
import ru.zonasb.backend.service.people.interfase.WorkerService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final PersonRepository personRepository;
    private final UserService userService;

    @Override
    public Worker getWorkerById(final long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("worker with that id is not found"));
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker createNewWorker(final WorkerDto workerDto) {
        if (personRepository.findPersonByEmail(workerDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("worker with that email is already exist");
        }
        if (personRepository.findPersonByPhone(workerDto.getPhone()).isPresent()) {
            throw new IllegalArgumentException("worker with that phone is already exist");
        }
        Person person = Person.builder()
                .name(workerDto.getName())
                .phone(workerDto.getPhone())
                .email(workerDto.getEmail())
                .build();

        person = personRepository.save(person);
        Worker worker = Worker.builder()
                .person(person)
                .build();
        return workerRepository.save(worker);
    }

    @Override
    public Worker createMySelfAsWorker() {
        User user = userService.getCurrentUser();
        Manager manager = user.getManager();
        Person person = manager.getPerson();
        if (workerRepository.findWorkerByPersonPhoneAndPersonEmail(person.getPhone(), person.getEmail()).isPresent()) {
            throw new IllegalArgumentException("that worker is already exist");
        }
        Worker worker = Worker.builder()
                .person(person)
                .build();
        return workerRepository.save(worker);
    }

    @Override
    public Worker updateWorkerById(final long id, final WorkerDto workerDto) {
        Worker worker = getWorkerById(id);
        Person person = worker.getPerson();
        if (!worker.getPerson().getEmail().equals(workerDto.getEmail())
                && personRepository.findPersonByEmail(workerDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("worker with that email is already exist");
        }
        if (!worker.getPerson().getPhone().equals(workerDto.getPhone())
                && personRepository.findPersonByPhone(workerDto.getPhone()).isPresent()) {
            throw new IllegalArgumentException("worker with that phone is already exist");
        }
        person.setName(workerDto.getName());
        person.setEmail(workerDto.getEmail());
        person.setPhone(workerDto.getPhone());
        personRepository.save(person);
        return worker;
    }

    @Override
    public void deleteWorkers(final DeleteDto deleteDto) {
        if (deleteDto.isDeleteAll()) {
            workerRepository.deleteAll();
        } else {
            workerRepository.deleteAllById(deleteDto.getIds());
        }
    }

}
