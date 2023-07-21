package ru.zonasb.backend.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "Priority title should not be Empty")
    @Size(max = 255)
    @Column(name = "title", unique = true)
    @Pattern(regexp = "^.*$")
    private String title;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Color code must be a valid 6-digit HEX color")
    @NotBlank
    @Column(name = "color", unique = true)
    private String color;

    @NotNull(message = "Priority weight cannot be null")
    @Min(value = 1, message = "Priority weight must be a positive integer")
    @Positive
    @Digits(integer = 10, fraction = 0)
    @Column(name = "priority_weight", unique = true, columnDefinition = "INT")
    private int weight;

    //    Связи
    @JsonIgnore
    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;
}
