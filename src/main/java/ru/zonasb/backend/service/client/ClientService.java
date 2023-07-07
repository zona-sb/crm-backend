package ru.zonasb.backend.service.client;

import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.model.people.Client;

public interface ClientService {
    Client getClientById(long id);
    ClientDto createNewClient(ClientDto clientDto);
    ClientDto updateClientById(long id, ClientDto clientDto);
}
