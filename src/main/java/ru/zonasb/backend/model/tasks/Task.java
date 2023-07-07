package ru.zonasb.backend.model.tasks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.zonasb.backend.model.people.Client;
import ru.zonasb.backend.model.people.Manager;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "status")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title should not be Empty")
    @Column(name = "address")
    private String address;

    @Column(name = "communicateion_date")
    private Date date;

    @Column(name = "operation_number")
    private Integer OperationNumber;

    @Column(name = "comment")
    private String comment;

    @Column(name = "isCompleted", columnDefinition = "boolean default false")
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
