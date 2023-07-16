package ru.zonasb.backend.service.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.people.PersonDto;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.repository.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    @Override
    public Person getPersonById(final long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("person with that id is not found"));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person createNewPerson(final PersonDto personDto) {
        if (personRepository.findPersonByEmail(personDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("person with that email is already exist");
        }
        if (personRepository.findPersonByPhone(personDto.getPhone()).isPresent()) {
            throw new IllegalArgumentException("person with that phone is already exist");
        }
        Person person = Person.builder()
                .name(personDto.getName())
                .phone(personDto.getPhone())
                .email(personDto.getEmail())
                .build();
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(final long id,final PersonDto personDto) {
        Person person = getPersonById(id);
        person.setName(personDto.getName());
        person.setPhone(personDto.getPhone());
        person.setEmail(personDto.getEmail());
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(final long id) {
        personRepository.deleteById(id);
    }
}
