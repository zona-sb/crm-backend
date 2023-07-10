package ru.zonasb.backend.service.worker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Manager;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.model.people.User;
import ru.zonasb.backend.model.people.Worker;
import ru.zonasb.backend.repository.PersonRepository;
import ru.zonasb.backend.repository.WorkerRepository;
import ru.zonasb.backend.service.person.PersonService;
import ru.zonasb.backend.service.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;
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
        Worker worker = Worker.builder()
                .person(person)
                .build();
        return workerRepository.save(worker);
    }

    @Override
    public Worker updateWorkerById(final long id, final WorkerDto workerDto) {
        Worker worker = getWorkerById(id);
        Person person = personService.getPersonById(worker.getPerson().getId());
        person.setName(workerDto.getName());
        person.setEmail(workerDto.getEmail());
        person.setPhone(workerDto.getPhone());
        personRepository.save(person);
        return worker;
    }

    @Override
    public void deleteWorkerById(final long id) {
        personService.deletePerson(getWorkerById(id).getPerson().getId());
    }


}
