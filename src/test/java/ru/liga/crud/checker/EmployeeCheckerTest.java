package ru.liga.crud.checker;

import org.junit.jupiter.api.Test;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;
import ru.liga.crud.type.Position;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeCheckerTest {
    private final EmployeeChecker employeeChecker = new EmployeeChecker();
    private final Employee invalidEmployee = new Employee();

    @Test
    void checkFirstName() {
        Employee validEmployee = new Employee();
        validEmployee.setFirstName("Петр");

        employeeChecker.checkFirstName(validEmployee);
        assertThat(validEmployee.isValid()).isTrue();

        employeeChecker.checkFirstName(invalidEmployee);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkLastName() {
        Employee validEmployee = new Employee();
        validEmployee.setLastName("Иванов");

        employeeChecker.checkLastName(validEmployee);
        assertThat(validEmployee.isValid()).isTrue();

        employeeChecker.checkLastName(invalidEmployee);
        assertThat(invalidEmployee.isValid()).isFalse();
    }

    @Test
    void checkSalary() {
        Employee developer = new Employee();
        developer.setPosition("Developer");

        developer.setSalary("65000");
        employeeChecker.checkSalary(developer, Position.DEVELOPER);
        assertThat(developer.isValid()).isTrue();

        developer.setSalary("25000");
        employeeChecker.checkSalary(developer, Position.DEVELOPER);
        assertThat(developer.isValid()).isFalse();
    }

    @Test
    void checkTesterFields() {
        Employee tester = new Employee();
        tester.setPosition("Tester");

        employeeChecker.checkTesterFields(tester);
        assertThat(tester.isValid()).isTrue();

        tester.setProgrammingLanguage("JavaScript");
        employeeChecker.checkTesterFields(tester);
        assertThat(tester.isValid()).isFalse();
    }

    @Test
    void checkDeveloperFields() {
        Employee developer = new Employee();
        developer.setPosition("Developer");

        developer.setProgrammingLanguage("Java");
        employeeChecker.checkDeveloperFields(developer);
        assertThat(developer.isValid()).isTrue();

        developer.setNumberOfSubordinates("15");
        employeeChecker.checkDeveloperFields(developer);
        assertThat(developer.isValid()).isFalse();
    }

    @Test
    void checkTeamLeadFields() {
        Employee teamLead = new Employee();
        teamLead.setPosition("TeamLead");

        teamLead.setNumberOfSubordinates("15");
        teamLead.setTelephoneNumber("+71111111111");
        employeeChecker.checkTeamLeadFields(teamLead);
        assertThat(teamLead.isValid()).isTrue();

        teamLead.setProgrammingLanguage("Java");
        employeeChecker.checkTeamLeadFields(teamLead);
        assertThat(teamLead.isValid()).isFalse();
    }

    @Test
    void checkManagerFields() {
        Employee manager = new Employee();
        manager.setPosition("Manager");

        manager.setEmail("test@qwer.ty");
        manager.setTelephoneNumber("+71111111111");
        employeeChecker.checkManagerFields(manager);
        assertThat(manager.isValid()).isTrue();

        manager.setProgrammingLanguage("Java");
        employeeChecker.checkManagerFields(manager);
        assertThat(manager.isValid()).isFalse();
    }

    @Test
    void checkTasks() {
        Employee employee = new Employee();
        Task task = new Task();

        task.setDescription("testTask");
        employee.getTasks().add(task);
        employeeChecker.checkTasks(employee);

        task.setDescription(null);
        employeeChecker.checkTasks(employee);
    }
}