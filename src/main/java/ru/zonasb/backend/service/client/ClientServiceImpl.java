package ru.zonasb.backend.service.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.model.people.Client;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.repository.ClientRepository;
import ru.zonasb.backend.repository.PersonRepository;
import ru.zonasb.backend.service.person.PersonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;


    @Override
    public Client getClientById(final long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("client with that id is not found"));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createNewClient(final ClientDto clientDto) {
        if (personRepository.findPersonByEmail(clientDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("client with that email is already exist");
        }

        Person person = Person.builder()
                .name(clientDto.getName())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .build();
        person = personRepository.save(person);
        Client client = Client
                .builder()
                .company(clientDto.getCompany())
                .comment(clientDto.getComment())
                .person(person)
                .build();
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientById(final long id, final ClientDto clientDto) {
        Client client = getClientById(id);
        Person person = personService.getPersonById(client.getPerson().getId());
        person.setPhone(clientDto.getPhone());
        person.setName(clientDto.getName());
        person.setEmail(clientDto.getEmail());
        personRepository.save(person);
        client.setCompany(clientDto.getCompany());
        client.setComment(clientDto.getComment());
        client.setPerson(person);
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(final long id) {

        personRepository.deleteById(getClientById(id).getPerson().getId());
    }
}
