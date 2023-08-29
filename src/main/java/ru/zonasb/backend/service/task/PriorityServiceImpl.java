package ru.zonasb.backend.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.DeleteDto;
import ru.zonasb.backend.dto.filtrationDto.PriorityFiltrationDto;
import ru.zonasb.backend.dto.task.PriorityDto;
import ru.zonasb.backend.model.tasks.Priority;
import ru.zonasb.backend.model.tasks.Task;
import ru.zonasb.backend.repository.PriorityRepository;
import ru.zonasb.backend.repository.TaskRepository;
import ru.zonasb.backend.service.task.interfase.PriorityService;

import java.util.*;
import java.util.function.Predicate;

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
    public Iterable<Priority> getAllPriorities(PriorityFiltrationDto priorityFiltrationDto) {
        return filtrationPriorities(priorityRepository.findAll(), priorityFiltrationDto);
    }

    @Override
    public Priority getPriorityById(final Long id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Priority with id " + id + " is not found"));
    }

    @Override
    public Priority updatePriority(final Long id, final PriorityDto priorityDto) {

        Priority priorityToUpdate = getPriorityById(id);

        if (!priorityToUpdate.getTitle().equals(priorityDto.getTitle())
                && priorityRepository.findPriorityByTitle(priorityDto.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Priority with this title is already exist");
        }
        if (priorityToUpdate.getWeight() != priorityDto.getWeight()
                && priorityRepository.findPriorityByWeight(priorityDto.getWeight()).isPresent()) {
            throw new IllegalArgumentException("Priority with this weight is already exist");
        }

        if (!priorityToUpdate.getColor().equals(priorityDto.getColor())
                && priorityRepository.findPriorityByColor(priorityDto.getColor()).isPresent()) {
            throw new IllegalArgumentException("Priority with this color is already exist");
        }

        priorityToUpdate.setTitle(priorityDto.getTitle());
        priorityToUpdate.setWeight(priorityDto.getWeight());
        priorityToUpdate.setColor(priorityDto.getColor());

        return priorityRepository.save(priorityToUpdate);
    }

    @Override
    public void deletePriority(DeleteDto deleteDto) {
        if (deleteDto.isDeleteAll()) {
            priorityRepository.deleteAll();
        } else {
            deleteDto.getIds().forEach(this::deletePriorityById);
        }
    }


    public void deletePriorityById(final Long id) {
        Priority priority = getPriorityById(id);

        Optional<List<Task>> optionalTasks = taskRepository.findTaskByPriority(priority);
        optionalTasks.ifPresent(tasks -> taskRepository.deleteAll(tasks));

        priorityRepository.deleteById(id);
    }

    private List<Priority> filtrationPriorities(List<Priority> priorities,
                                                PriorityFiltrationDto priorityFiltrationDto) {
        List<Predicate<Priority>> predicates = new ArrayList<>();
        if (priorityFiltrationDto.getTitle() != null && !priorityFiltrationDto.getTitle().isEmpty()) {
            Predicate<Priority> predicateTitle = x -> x.getTitle().contains(priorityFiltrationDto.getTitle());
            predicates.add(predicateTitle);
        }
        if (priorityFiltrationDto.getWeight() != null) {
            Predicate<Priority> predicateWeight = x -> Objects.equals(priorityFiltrationDto.getWeight(), x.getWeight());
            predicates.add(predicateWeight);
        }
        if (priorityFiltrationDto.getColor() != null && !priorityFiltrationDto.getColor().isEmpty()) {
            Predicate<Priority> predicateColor = x -> Objects.equals(x.getColor(), priorityFiltrationDto.getColor());
            predicates.add(predicateColor);
        }
        return priorities.stream().filter(x -> predicates.stream().allMatch(predicate -> predicate.test(x))).toList();
    }





}
