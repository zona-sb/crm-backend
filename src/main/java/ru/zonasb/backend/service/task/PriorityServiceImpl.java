package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.PriorityRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.task.interfase.PriorityService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    PriorityRepository priorityRepository;
    TaskRepository taskRepository;

    @Override
    public Priority createNewPriority(final PriorityDto priorityDto) {

        if (priorityRepository.findPriorityByTitle(priorityDto.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Priority with this title is already exist");
        }
        if (priorityRepository.findPriorityByWeight(priorityDto.getWeight()).isPresent()) {
            throw new IllegalArgumentException("Priority with this weight is already exist");
        }
        if (priorityRepository.findPriorityByColor(priorityDto.getColor()).isPresent()) {
            throw new IllegalArgumentException("Priority with this color is already exist");
        }

        System.out.println(priorityDto.getWeight());

        Priority priority = Priority.builder()
                .title(priorityDto.getTitle())
                .weight(priorityDto.getWeight())
                .color(priorityDto.getColor())
                .build();

        return priorityRepository.save(priority);
    }

    @Override
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public Priority getPriorityById(final Long id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Priority with id " + id + " is not found"));
    }

    @Override
    public Priority updatePriority(final Long id, final PriorityDto priorityDto) {

        Priority priorityToUpdate = getPriorityById(id);
        priorityToUpdate.setTitle(priorityDto.getTitle());
        priorityToUpdate.setWeight(priorityDto.getWeight());
        priorityToUpdate.setColor(priorityDto.getColor());

        return priorityRepository.save(priorityToUpdate);

    }

    @Override
    public Priority patchUpdatePriority(Long id, Map<String, Object> update) {

        Priority priorityToUpdate = getPriorityById(id);

        if (update.containsKey("title")) {
            priorityToUpdate.setTitle((String) update.get("title"));
        }

        if (update.containsKey("color")) {
            priorityToUpdate.setColor((String) update.get("color"));
        }

        if (update.containsKey("weight")) {
            priorityToUpdate.setWeight((Integer) update.get("weight"));
        }

        return priorityRepository.save(priorityToUpdate);
    }

    @Override
    public void deletePriorityById(final Long id) {
        Priority priority = getPriorityById(id);

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByPriority(priority);
        optionalTasks.ifPresent(tasks -> taskRepository.deleteAll(tasks));

        priorityRepository.deleteById(id);
    }

    @Override
    public void bulkDeletePriority(List<Long> ids) {
        ids.forEach(this::deletePriorityById);
    }
}