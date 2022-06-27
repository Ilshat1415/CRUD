package ru.liga.crud.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.liga.crud.testdata.EmployeeTestData.*;

import org.junit.jupiter.api.Test;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.type.Position;

class EmployeeCheckerTest {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Test
    void checkFirstName_ValidFirstName_True() {
        Employee validEmployee = getEmployeeWithValidName();
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
        Employee validEmployee = getEmployeeWithValidName();
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
        Employee validEmployee = getEmployeeWithValidSalary();
        employeeChecker.checkSalary(validEmployee, Position.DEVELOPER);
        assertThat(validEmployee.isValid()).isTrue();
    }

    @Test
    void checkSalary_InvalidSalary_False() {
        Employee invalidEmployee = getEmployeeWithInvalidSalary();
        employeeChecker.checkSalary(invalidEmployee, Position.DEVELOPER);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkTesterFields_ValidTesterFields_True() {
        Employee validTester = getValidTester();
        employeeChecker.checkTesterFields(validTester);
        assertThat(validTester.isValid()).isTrue();
    }

    @Test
    void checkTesterFields_InvalidTesterFields_False() {
        Employee invalidTester = getInvalidTester();
        employeeChecker.checkTesterFields(invalidTester);
        assertThat(invalidTester.isValid()).isFalse();
    }

    @Test
    void checkDeveloperFields_ValidDeveloperFields_True() {
        Employee validDeveloper = getValidDeveloper();
        employeeChecker.checkDeveloperFields(validDeveloper);
        assertThat(validDeveloper.isValid()).isTrue();
    }

    @Test
    void checkDeveloperFields_InvalidDeveloperFields_False() {
        Employee invalidDeveloper = getInvalidDeveloper();
        employeeChecker.checkDeveloperFields(invalidDeveloper);
        assertThat(invalidDeveloper.isValid()).isFalse();
    }

    @Test
    void checkTeamLeadFields_ValidTeamLead_True() {
        Employee validTeamLead = getValidTeamLead();
        employeeChecker.checkTeamLeadFields(validTeamLead);
        assertThat(validTeamLead.isValid()).isTrue();
    }

    @Test
    void checkTeamLeadFields_InvalidTeamLead_False() {
        Employee invalidTeamLead = getInvalidTeamLead();
        employeeChecker.checkTeamLeadFields(invalidTeamLead);
        assertThat(invalidTeamLead.isValid()).isFalse();
    }

    @Test
    void checkManagerFields_ValidManager_True() {
        Employee validManager = getValidManager();
        employeeChecker.checkManagerFields(validManager);
        assertThat(validManager.isValid()).isTrue();
    }

    @Test
    void checkManagerFields_InvalidManager_False() {
        Employee invalidManager = getInvalidManager();
        employeeChecker.checkManagerFields(invalidManager);
        assertThat(invalidManager.isValid()).isFalse();
    }

    @Test
    void checkTasks_ValidTasks_True() {
        Employee employee = getEmployeeWithValidTask();
        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isTrue();
    }

    @Test
    void checkTasks_InvalidTasks_False() {
        Employee employee = getEmployeeWithInvalidTask();
        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isFalse();
    }
}
