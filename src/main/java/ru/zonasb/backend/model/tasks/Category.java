package ru.zonasb.backend.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category title should not be Empty")
    @Size(max = 255)
    @Column(name = "title", unique = true)
    private String categoryTitle;

//    Связи

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Status> statuses;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}
