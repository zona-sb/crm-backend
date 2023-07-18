package ru.zonasb.backend.controller.people;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.service.people.interfase.PersonService;

import java.util.List;

import static ru.zonasb.backend.controller.people.PersonController.PERSON_CONTROLLER_PATH;

// вспомогательный контроллер для тестирования
@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + PERSON_CONTROLLER_PATH)
public class PersonController {
    public static final String PERSON_CONTROLLER_PATH = "/people";
    public static final String ID = "/{id}";
    private final PersonService personService;

    @GetMapping
    List<Person> getAllPeople() {
        return personService.getAllPersons();
    }

}
