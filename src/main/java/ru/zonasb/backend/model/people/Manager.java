package ru.zonasb.backend.model.people;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.zonasb.backend.model.tasks.Task;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    Связи

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Task> tasks;

}
