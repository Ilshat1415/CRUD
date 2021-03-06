package ru.liga.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.crud.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
