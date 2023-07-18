package ru.zonasb.backend.service.people.interfase;


import ru.zonasb.backend.dto.people.PersonDto;
import ru.zonasb.backend.model.people.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(long id);

    List<Person> getAllPersons();

    Person createNewPerson(PersonDto personDto);

    Person updatePerson(long id, PersonDto personDto);

    void deletePerson(long id);

}
