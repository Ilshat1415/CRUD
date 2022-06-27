package ru.liga.crud.checker;

import org.junit.jupiter.api.Test;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;
import ru.liga.crud.type.Position;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeCheckerTest {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Test
    void checkFirstName_ValidFirstName_True() {
        Employee validEmployee = new Employee();
        validEmployee.setFirstName("Петр");

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
        Employee validEmployee = new Employee();
        validEmployee.setLastName("Иванов");

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
        Employee validEmployee = new Employee();
        validEmployee.setPosition("Developer");
        validEmployee.setSalary("65000");

        employeeChecker.checkSalary(validEmployee, Position.DEVELOPER);
        assertThat(validEmployee.isValid()).isTrue();
    }

    @Test
    void checkSalary_InvalidSalary_False() {
        Employee invalidEmployee = new Employee();
        invalidEmployee.setPosition("Developer");
        invalidEmployee.setSalary("25000");

        employeeChecker.checkSalary(invalidEmployee, Position.DEVELOPER);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkTesterFields_ValidTesterFields_True() {
        Employee validTester = new Employee();
        validTester.setPosition("Tester");

        employeeChecker.checkTesterFields(validTester);
        assertThat(validTester.isValid()).isTrue();
    }

    @Test
    void checkTesterFields_InvalidTesterFields_False() {
        Employee invalidTester = new Employee();
        invalidTester.setPosition("Tester");
        invalidTester.setProgrammingLanguage("JavaScript");

        employeeChecker.checkTesterFields(invalidTester);
        assertThat(invalidTester.isValid()).isFalse();
    }

    @Test
    void checkDeveloperFields_ValidDeveloperFields_True() {
        Employee developer = new Employee();
        developer.setPosition("Developer");
        developer.setProgrammingLanguage("Java");

        employeeChecker.checkDeveloperFields(developer);
        assertThat(developer.isValid()).isTrue();
    }

    @Test
    void checkDeveloperFields_InvalidDeveloperFields_False() {
        Employee developer = new Employee();
        developer.setPosition("Developer");
        developer.setNumberOfSubordinates("15");

        employeeChecker.checkDeveloperFields(developer);
        assertThat(developer.isValid()).isFalse();
    }

    @Test
    void checkTeamLeadFields_ValidTeamLead_True() {
        Employee validTeamLead = new Employee();
        validTeamLead.setPosition("TeamLead");
        validTeamLead.setNumberOfSubordinates("15");
        validTeamLead.setTelephoneNumber("+71111111111");

        employeeChecker.checkTeamLeadFields(validTeamLead);
        assertThat(validTeamLead.isValid()).isTrue();
    }

    @Test
    void checkTeamLeadFields_InvalidTeamLead_False() {
        Employee invalidTeamLead = new Employee();
        invalidTeamLead.setPosition("TeamLead");
        invalidTeamLead.setProgrammingLanguage("Java");

        employeeChecker.checkTeamLeadFields(invalidTeamLead);
        assertThat(invalidTeamLead.isValid()).isFalse();
    }

    @Test
    void checkManagerFields_ValidManager_True() {
        Employee validManager = new Employee();
        validManager.setPosition("Manager");
        validManager.setEmail("test@qwer.ty");
        validManager.setTelephoneNumber("+71111111111");

        employeeChecker.checkManagerFields(validManager);
        assertThat(validManager.isValid()).isTrue();
    }

    @Test
    void checkManagerFields_InvalidManager_False() {
        Employee invalidManager = new Employee();
        invalidManager.setPosition("Manager");
        invalidManager.setProgrammingLanguage("Java");

        employeeChecker.checkManagerFields(invalidManager);
        assertThat(invalidManager.isValid()).isFalse();
    }

    @Test
    void checkTasks_ValidTasks_True() {
        Task validTask = new Task();
        validTask.setDescription("testTask");

        Employee employee = new Employee();
        employee.getTasks().add(validTask);

        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isTrue();
    }

    @Test
    void checkTasks_InvalidTasks_False() {
        Task invalidTask = new Task();

        Employee employee = new Employee();
        employee.getTasks().add(invalidTask);

        employeeChecker.checkTasks(employee);
        assertThat(employee.isValid()).isFalse();
    }
}