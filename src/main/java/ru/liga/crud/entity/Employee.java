package ru.liga.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employees")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String salary;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "programming_language")
    private String programmingLanguage;
    @Column(name = "number_of_subordinates")
    private String numberOfSubordinates;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_tasks",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_uuid")
    )
    private List<Task> tasks = new ArrayList<>();

    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public void isNotValid() {
        this.isValid = false;
    }
}
