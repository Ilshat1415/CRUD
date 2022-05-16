package ru.liga.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private int salary;
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
}
