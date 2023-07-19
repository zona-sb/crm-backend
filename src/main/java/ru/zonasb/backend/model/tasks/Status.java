package ru.zonasb.backend.model.tasks;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "status",
        uniqueConstraints = @UniqueConstraint(columnNames = {"category_id", "title"}))
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be Empty")
    @Size(max = 255)
    @Column(name = "title")
    private String statusTitle;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

//    Связи

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private List<Task> tasks;

}
