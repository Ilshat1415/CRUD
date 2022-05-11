package ru.liga.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = (SELECT MIN(id) FROM Employee)")
    void deleteOldId();
}
