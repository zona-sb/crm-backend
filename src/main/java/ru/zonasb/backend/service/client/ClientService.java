package ru.zonasb.backend.service.client;

import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.model.people.Client;

import java.util.List;

public interface ClientService {
    Client getClientById(long id);
    List<Client> getAllClients();
    Client createNewClient(ClientDto clientDto);
    Client updateClientById(long id, ClientDto clientDto);
    void deleteById(long id);
}
