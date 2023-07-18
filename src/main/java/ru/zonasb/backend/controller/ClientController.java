package ru.zonasb.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.model.people.Client;
import ru.zonasb.backend.service.client.ClientService;

import java.util.List;

import static ru.zonasb.backend.controller.ClientController.CLIENT_CONTROLLER_PATH;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + CLIENT_CONTROLLER_PATH)
public class ClientController {
    public static final String CLIENT_CONTROLLER_PATH = "/clients";
    public static final String ID = "/{id}";
    private final ClientService clientService;

    @GetMapping(ID)
    public Client getClientById(@PathVariable long id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createNewClient(@RequestBody @Valid ClientDto clientDto) {
        return clientService.createNewClient(clientDto);
    }

    @PutMapping(ID)
    public Client updateClient(@PathVariable long id, @RequestBody @Valid ClientDto clientDto) {
        return clientService.updateClientById(id,clientDto);
    }

    @DeleteMapping(ID)
    public void deleteClientById(@PathVariable long id) {
        clientService.deleteById(id);
    }

}
