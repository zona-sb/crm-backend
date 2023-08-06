package ru.zonasb.backend.service.people.interfase;

import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.model.people.Client;

import java.util.List;

public interface ClientService {
    Client getClientById(long id);
    Client getClientByEmail(String email);
    Client getClientByPhone(String phone);
    List<Client> getAllClients();
    Client createNewClient(ClientDto clientDto);
    Client updateClientById(long id, ClientDto clientDto);
    void deleteById(long id);
}
