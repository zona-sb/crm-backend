package ru.zonasb.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.fromFront.RegistrationDto;
import ru.zonasb.backend.service.registration.RegistrationService;

import static ru.zonasb.backend.controller.RegistrationController.REGISTRATION_CONTROLLER_PATH;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + REGISTRATION_CONTROLLER_PATH)
public class RegistrationController {
    public static final String REGISTRATION_CONTROLLER_PATH = "/registration";
    private RegistrationService registrationService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RegistrationDto registration(@RequestBody @Valid RegistrationDto registrationDto) {
        return registrationService.registrationNewManager(registrationDto);
    }

}
