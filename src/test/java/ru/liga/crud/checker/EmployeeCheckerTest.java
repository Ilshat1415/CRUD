package ru.liga.crud.checker;

import org.junit.jupiter.api.Test;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.testdata.EmployeeTestData;
import ru.liga.crud.type.Position;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeCheckerTest {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Test
    void checkFirstName_ValidFirstName_True() {
        Employee validEmployee = EmployeeTestData.getEmployeeWithValidName();
        employeeChecker.checkFirstName(validEmployee);
        assertThat(validEmployee.isValid()).isTrue();
    }

    @Test
    void checkFirstName_InvalidFirstName_False() {
        Employee invalidEmployee = new Employee();
        employeeChecker.checkFirstName(invalidEmployee);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkLastName_ValidLastName_True() {
        Employee validEmployee = EmployeeTestData.getEmployeeWithValidName();
        employeeChecker.checkLastName(validEmployee);
        assertThat(validEmployee.isValid()).isTrue();
    }

    @Test
    void checkLastName_InvalidLastName_False() {
        Employee invalidEmployee = new Employee();
        employeeChecker.checkLastName(invalidEmployee);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkSalary_ValidSalary_True() {
        Employee validEmployee = EmployeeTestData.getEmployeeWithValidSalary();
        employeeChecker.checkSalary(validEmployee, Position.DEVELOPER);
        assertThat(validEmployee.isValid()).isTrue();
    }

    @Test
    void checkSalary_InvalidSalary_False() {
        Employee invalidEmployee = EmployeeTestData.getEmployeeWithInvalidSalary();
        employeeChecker.checkSalary(invalidEmployee, Position.DEVELOPER);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkTesterFields_ValidTesterFields_True() {
        Employee validTester = EmployeeTestData.getValidTester();
        employeeChecker.checkTesterFields(validTester);
        assertThat(validTester.isValid()).isTrue();
    }

    @Test
    void checkTesterFields_InvalidTesterFields_False() {
        Employee invalidTester = EmployeeTestData.getInvalidTester();
        employeeChecker.checkTesterFields(invalidTester);
        assertThat(invalidTester.isValid()).isFalse();
    }

    @Test
    void checkDeveloperFields_ValidDeveloperFields_True() {
        Employee validDeveloper = EmployeeTestData.getValidDeveloper();
        employeeChecker.checkDeveloperFields(validDeveloper);
        assertThat(validDeveloper.isValid()).isTrue();
    }

    @Test
    void checkDeveloperFields_InvalidDeveloperFields_False() {
        Employee invalidDeveloper = EmployeeTestData.getInvalidDeveloper();
        employeeChecker.checkDeveloperFields(invalidDeveloper);
        assertThat(invalidDeveloper.isValid()).isFalse();
    }

    @Test
    void checkTeamLeadFields_ValidTeamLead_True() {
        Employee validTeamLead = EmployeeTestData.getValidTeamLead();
        employeeChecker.checkTeamLeadFields(validTeamLead);
        assertThat(validTeamLead.isValid()).isTrue();
    }

    @Test
    void checkTeamLeadFields_InvalidTeamLead_False() {
        Employee invalidTeamLead = EmployeeTestData.getInvalidTeamLead();
        employeeChecker.checkTeamLeadFields(invalidTeamLead);
        assertThat(invalidTeamLead.isValid()).isFalse();
    }

    @Test
    void checkManagerFields_ValidManager_True() {
        Employee validManager = EmployeeTestData.getValidManager();
        employeeChecker.checkManagerFields(validManager);
        assertThat(validManager.isValid()).isTrue();
    }

    @Test
    void checkManagerFields_InvalidManager_False() {
        Employee invalidManager = EmployeeTestData.getInvalidManager();
        employeeChecker.checkManagerFields(invalidManager);
        assertThat(invalidManager.isValid()).isFalse();
    }

    @Test
    void checkTasks_ValidTasks_True() {
        Employee employee = EmployeeTestData.getEmployeeWithValidTask();
        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isTrue();
    }

    @Test
    void checkTasks_InvalidTasks_False() {
        Employee employee = EmployeeTestData.getEmployeeWithInvalidTask();
        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isFalse();
    }
}