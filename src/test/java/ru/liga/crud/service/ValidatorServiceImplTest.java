package ru.liga.crud.service;

import org.junit.jupiter.api.Test;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.testdata.EmployeeTestData;
import ru.liga.crud.type.Status;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidatorServiceImplTest {
    private final ValidatorServiceImpl validatorService = new ValidatorServiceImpl();

    @Test
    void validate_ValidEmployee_Success() {
        Employee employee = EmployeeTestData.getEmployeeWithValidSalary();
        ResponseEmployee responseEmployee = validatorService.validate(employee);
        assertThat(responseEmployee.getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void validate_InvalidEmployee_Problem() {
        Employee employee = EmployeeTestData.getEmployeeWithInvalidSalary();
        ResponseEmployee responseEmployee = validatorService.validate(employee);
        assertThat(responseEmployee.getStatus()).isEqualTo(Status.PROBLEM.name());
    }
}