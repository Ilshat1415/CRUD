package ru.liga.crud.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liga.crud.initializer.CrudInitializer;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.type.Status;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmployeeServiceImplTest extends CrudInitializer {
    private final static String UUID_TEST = "43fd917b-59bb-4887-a55c-d8cafd354b73";
    private final static String UUID_TEST_DELETE = "118f1ed5-53d5-46eb-b1d3-3f346e6df17b";
    private final EmployeeServiceImpl employeeService;

    @Autowired()
    public EmployeeServiceImplTest(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    void findByValidUuid() {
        ResponseEmployee responseEmployee = employeeService.findByUuid(UUID_TEST);

        assertThat(responseEmployee.getStatus()).isEqualTo(Status.SUCCESS.name());
        assertThat(responseEmployee.getEmployee().getUuid()).isEqualTo(UUID_TEST);
    }

    @Test
    void saveValidEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Tester");
        employee.setLastName("Tester");
        employee.setPosition("Tester");
        employee.setSalary("50000");

        assertThat(employeeService.saveEmployee(employee).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void updateValidEmployee() {
        Employee employee = new Employee();
        employee.setUuid(UUID_TEST);
        employee.setFirstName("Tester");
        employee.setLastName("Tester");
        employee.setPosition("Tester");
        employee.setSalary("80000");

        assertThat(employeeService.updateEmployee(employee).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void deleteEmployeeByValidUuid() {
        assertThat(employeeService.deleteEmployee(UUID_TEST_DELETE).getStatus()).isEqualTo(Status.SUCCESS.name());
    }

    @Test
    void findByInvalidUuid() {
        ResponseEmployee responseEmployee = employeeService.findByUuid("invalidUuid");

        assertThat(responseEmployee.getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void saveInvalidEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Tester");
        employee.setLastName("Tester");
        employee.setPosition("Tester");
        employee.setSalary("invalidSalary");

        assertThat(employeeService.saveEmployee(employee).getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void updateInvalidEmployee() {
        Employee employee = new Employee();
        employee.setUuid(UUID_TEST);
        employee.setFirstName("Tester");
        employee.setLastName("Tester");
        employee.setPosition("Tester");
        employee.setSalary("invalidSalary");

        assertThat(employeeService.updateEmployee(employee).getStatus()).isEqualTo(Status.PROBLEM.name());
    }

    @Test
    void deleteEmployeeByInvalidUuid() {
        assertThat(employeeService.deleteEmployee("inavalidUuid").getStatus()).isEqualTo(Status.PROBLEM.name());
    }
}