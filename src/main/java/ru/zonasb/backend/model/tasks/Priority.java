package ru.zonasb.backend.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private String title;

    @Pattern(regexp = "^#[0-9a-fA-F]{6}$",
            message = "Color code must be a valid HEX color")
    @Column(name = "color", unique = true)
    private String color;

    @NotNull(message = "Priority weight cannot be null")
    @Min(value = 1, message = "Priority weight must be greater than 0")
    @Column(name = "priority_weight", unique = true)
    private int weight;

    //    Связи
    @JsonIgnore
    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;
}
