package ru.liga.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User { //todo лучше оперировать название классов, как и таблиц)) Так уменьшается вероятность запутать с сущностях 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
}
