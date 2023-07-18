package ru.zonasb.backend.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "priority")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title should not be Empty")
    @Column(name = "title", unique = true)
    private String title;

    @NotBlank(message = "title should not be Empty")
    @Column(name = "color", unique = true)
    private String color;

    @Column(name = "priority_weight", unique = true)
    private int weight;

//    Связи
    @JsonIgnore
    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;
}
