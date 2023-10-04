package ru.zonasb.backend.config.component;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import ru.zonasb.backend.dto.people.ClientDto;
import ru.zonasb.backend.dto.registration.RegistrationDto;
import ru.zonasb.backend.dto.task.CategoryDto;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.dto.task.StatusDto;
import ru.zonasb.backend.dto.task.TaskDto;
import ru.zonasb.backend.repository.CategoryRepository;
import ru.zonasb.backend.repository.PriorityRepository;
import ru.zonasb.backend.repository.StatusRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.people.interfase.*;
import ru.zonasb.backend.service.registration.RegistrationService;
import ru.zonasb.backend.service.task.interfase.CategoryService;
import ru.zonasb.backend.service.task.interfase.PriorityService;
import ru.zonasb.backend.service.task.interfase.StatusService;
import ru.zonasb.backend.service.task.interfase.TaskService;

import javax.sql.DataSource;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class AppDataInitializer {
    private final DataSource dataSource;
    private final RoleService roleService;
    private final UserService userService;
    private final ClientService clientService;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final StatusService statusService;
    private final TaskService taskService;
    private final RegistrationService registrationService;
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final TaskRepository taskRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        if (roleService.getAllRoles().isEmpty()) {
            ResourceDatabasePopulator resourceDatabasePopulator
                    = new ResourceDatabasePopulator(false, false,
                    "UTF-8", new ClassPathResource("sql/dictionaries/insert_role.sql"));
            resourceDatabasePopulator.execute(dataSource);
        }
        if (userService.getAllUsers().isEmpty()) {
            RegistrationDto registrationDto = RegistrationDto.builder()
                    .name("user")
                    .phone("+79999999999")
                    .email("user@mail.ru")
                    .password("pass")
                    .build();
            registrationService.registrationNewManager(registrationDto);
        }
        if (clientService.getAllClients().isEmpty()) {
            ClientDto clientDto = ClientDto.builder()
                    .name("Клиент")
                    .phone("+78888888888")
                    .email("client@client")
                    .company("company")
                    .comment("chert")
                    .build();
            clientService.createNewClient(clientDto);
        }
        if (categoryRepository.findAll().isEmpty()) {
            CategoryDto categoryDto1 = CategoryDto.builder()
                    .categoryTitle("Монтажи")
                    .build();
            categoryService.createNewCategory(categoryDto1);
            CategoryDto categoryDto2 = CategoryDto.builder()
                    .categoryTitle("Ремонты")
                    .build();
            categoryService.createNewCategory(categoryDto2);
            CategoryDto categoryDto3 = CategoryDto.builder()
                    .categoryTitle("Заказы")
                    .build();
            categoryService.createNewCategory(categoryDto3);
            CategoryDto categoryDto4 = CategoryDto.builder()
                    .categoryTitle("Дефектовки")
                    .build();
            categoryService.createNewCategory(categoryDto4);
        }
        if (statusRepository.findAll().isEmpty()) {
            for (long i = 1; i <= 4; i++) {
                StatusDto statusDto1 = StatusDto.builder()
                        .categoryId(i)
                        .statusTitle("Новая")
                        .build();
                statusService.createNewStatus(statusDto1);
                StatusDto statusDto2 = StatusDto.builder()
                        .categoryId(i)
                        .statusTitle("Первый поинт")
                        .build();
                statusService.createNewStatus(statusDto2);
                StatusDto statusDto3 = StatusDto.builder()
                        .categoryId(i)
                        .statusTitle("Второй поинт")
                        .build();
                statusService.createNewStatus(statusDto3);
                StatusDto statusDto4 = StatusDto.builder()
                        .categoryId(i)
                        .statusTitle("Третий поинт")
                        .build();
                statusService.createNewStatus(statusDto4);
                StatusDto statusDto5 = StatusDto.builder()
                        .categoryId(i)
                        .statusTitle("Завершение")
                        .build();
                statusService.createNewStatus(statusDto5);
            }
        }
        if (priorityRepository.findAll().isEmpty()) {
            PriorityDto priorityDto1 = PriorityDto.builder()
                    .title("Высокий")
                    .color("#541212")
                    .weight(1)
                    .build();
            priorityService.createNewPriority(priorityDto1);
            PriorityDto priorityDto2 = PriorityDto.builder()
                    .title("Средний")
                    .color("#d8ce46")
                    .weight(2)
                    .build();
            priorityService.createNewPriority(priorityDto2);
            PriorityDto priorityDto3 = PriorityDto.builder()
                    .title("Низкий")
                    .color("#46d850")
                    .weight(3)
                    .build();
            priorityService.createNewPriority(priorityDto3);
        }
        if (taskRepository.findAll().isEmpty()) {



            for (long i = 1; i <= 75; i++) {
                TaskDto taskDto = TaskDto.builder()
                        .address("address")
                        .date(LocalDate.parse("2023-10-10"))
                        .operationNumber((int) i)
                        .comment("comment")
                        .completed(false)
                        .statusId(i % 5 + 1)
                        .priorityId(i % 3 + 1)
                        .categoryId(i % 4 + 1)
                        .clientId(1L)
                        .build();
                taskService.createNewTask(taskDto);
            }
        }
    }


}
