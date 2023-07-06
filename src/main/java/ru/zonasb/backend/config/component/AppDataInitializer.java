package ru.zonasb.backend.config.component;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import ru.zonasb.backend.service.role.RoleService;

import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class AppDataInitializer {
    private final DataSource dataSource;
    private RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        if (roleService.getAllRoles().isEmpty()) {
            ResourceDatabasePopulator resourceDatabasePopulator
                    = new ResourceDatabasePopulator(false, false,
                    "UTF-8", new ClassPathResource("sql/dictionaries/insert_role.sql"));
            resourceDatabasePopulator.execute(dataSource);
        }
    }
}
