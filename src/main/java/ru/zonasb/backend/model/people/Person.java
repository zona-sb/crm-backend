package ru.zonasb.backend.model.people;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name should not be Empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "phone should not be Empty")
    @Size(min = 11, max = 12)
    @Pattern(regexp = "^\\+?[0-9]{11}$", message = "phone number should be like \"+11111111111\"")
    private String phone;

    @NotBlank(message = "email should not be Empty")
    @Email(message = "Incorrect Email")
    @Column(name = "email")
    private String email;

//    Связи
    @JsonIgnore
    @OneToOne(mappedBy = "person")
    private Manager manager;

    @JsonIgnore
    @OneToOne(mappedBy = "person")
    private Worker worker;

    @JsonIgnore
    @OneToOne(mappedBy = "person")
    private Client client;

}
