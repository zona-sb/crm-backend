package ru.zonasb.backend.service.registration;

import ru.zonasb.backend.dto.registration.RegistrationDto;

public interface RegistrationService {
    RegistrationDto registrationNewManager(RegistrationDto registrationDto);
}
