package ru.zonasb.backend.model.tasks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title should not be Empty")
    @Column(name = "title")
    private String title;

//    Связи

    @OneToMany(mappedBy = "category")
    private List<Status> statuses;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}
