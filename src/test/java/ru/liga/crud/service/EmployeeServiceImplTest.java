package ru.liga.crud.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liga.crud.initializer.InitializerForTests;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.type.Status;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmployeeServiceImplTest extends InitializerForTests {
    private final static String UUID_TEST = "43fd917b-59bb-4887-a55c-d8cafd354b73";
    private final static String UUID_TEST_DELETE = "118f1ed5-53d5-46eb-b1d3-3f346e6df17b";
    private final EmployeeServiceImpl employeeService;

    @Autowired()
    public EmployeeServiceImplTest(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    void findByUuid_ValidUuid_Success() {
        ResponseEmployee responseEmployee = employeeService.findByUuid(UUID_TEST);

        assertThat(responseEmployee.getStatus()).isEqualTo(Status.SUCCESS.name());
        assertThat(responseEmployee.getEmployee().getUuid()).isEqualTo(UUID_TEST);
    }

    @Test
    void findByUuid_InvalidUuid_Problem() {
        ResponseEmployee responseEmployee = employeeService.findByUuid("invalidUuid");

        assertThat(responseEmployee.getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void saveEmployee_ValidEmployee_Success() {
        Employee validEmployee = new Employee();
        validEmployee.setFirstName("Tester");
        validEmployee.setLastName("Tester");
        validEmployee.setPosition("Tester");
        validEmployee.setSalary("50000");

        assertThat(employeeService.saveEmployee(validEmployee).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void saveEmployee_InvalidEmployee_Problem() {
        Employee invalidEmployee = new Employee();
        invalidEmployee.setFirstName("Tester");
        invalidEmployee.setLastName("Tester");
        invalidEmployee.setPosition("Tester");
        invalidEmployee.setSalary("invalidSalary");

        assertThat(employeeService.saveEmployee(invalidEmployee).getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void updateEmployee_ValidEmployee_Success() {
        Employee validEmployee = new Employee();
        validEmployee.setUuid(UUID_TEST);
        validEmployee.setFirstName("Tester");
        validEmployee.setLastName("Tester");
        validEmployee.setPosition("Tester");
        validEmployee.setSalary("80000");

        assertThat(employeeService.updateEmployee(validEmployee).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void updateEmployee_InvalidEmployee_Problem() {
        Employee invalidEmployee = new Employee();
        invalidEmployee.setUuid(UUID_TEST);
        invalidEmployee.setFirstName("Tester");
        invalidEmployee.setLastName("Tester");
        invalidEmployee.setPosition("Tester");
        invalidEmployee.setSalary("invalidSalary");

        assertThat(employeeService.updateEmployee(invalidEmployee).getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void deleteEmployeeByUuid_ValidUuid_Success() {
        assertThat(employeeService.deleteEmployee(UUID_TEST_DELETE).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void deleteEmployeeByUuid_InvalidUuid_Problem() {
        assertThat(employeeService.deleteEmployee("inavalidUuid").getStatus()).isEqualTo(Status.PROBLEM.name());
    }
}