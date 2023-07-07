package ru.zonasb.backend.service.worker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.people.WorkerDto;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.model.people.User;
import ru.zonasb.backend.model.people.Worker;
import ru.zonasb.backend.repository.PersonRepository;
import ru.zonasb.backend.repository.WorkerRepository;
import ru.zonasb.backend.service.person.PersonService;
import ru.zonasb.backend.service.user.UserService;

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
    public WorkerDto createNewWorker(final WorkerDto workerDto) {
        Person person = Person.builder()
                .name(workerDto.getName())
                .phone(workerDto.getPhone())
                .email(workerDto.getEmail())
                .build();
        person = personRepository.save(person);
        User user = userService.getCurrentUser();
        Worker worker = Worker.builder()
                .person(person)
                .user(user)
                .build();
        worker = workerRepository.save(worker);
        workerDto.setId(worker.getId());
        return workerDto;
    }

    @Override
    public WorkerDto updateWorkerById(final long id, final WorkerDto workerDto) {
        Worker worker = getWorkerById(id);
        Person person = personService.getPersonById(worker.getPerson().getId());
        person.setName(workerDto.getName());
        person.setEmail(workerDto.getEmail());
        person.setPhone(workerDto.getPhone());
        personRepository.save(person);
        workerDto.setId(id);
        return workerDto;
    }
}
