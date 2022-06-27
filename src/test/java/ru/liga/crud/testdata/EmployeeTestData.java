package ru.liga.crud.testdata;

import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;

public class EmployeeTestData {

    public static Employee getEmployeeWithValidName() {
        Employee validEmployee = new Employee();
        validEmployee.setFirstName("Петр");
        validEmployee.setLastName("Иванов");

        return validEmployee;
    }

    public static Employee getValidTester() {
        Employee validTester = getEmployeeWithValidName();
        validTester.setPosition("Tester");

        return validTester;
    }

    public static Employee getInvalidTester() {
        Employee invalidTester = getEmployeeWithValidName();
        invalidTester.setPosition("Tester");
        invalidTester.setProgrammingLanguage("JavaScript");

        return invalidTester;
    }

    public static Employee getValidDeveloper() {
        Employee validDeveloper = getEmployeeWithValidName();
        validDeveloper.setPosition("Developer");
        validDeveloper.setProgrammingLanguage("Java");

        return validDeveloper;
    }

    public static Employee getInvalidDeveloper() {
        Employee invalidDeveloper = getEmployeeWithValidName();
        invalidDeveloper.setPosition("Developer");
        invalidDeveloper.setNumberOfSubordinates("15");

        return invalidDeveloper;
    }

    public static Employee getValidTeamLead() {
        Employee validTeamLead = getEmployeeWithValidName();
        validTeamLead.setPosition("TeamLead");
        validTeamLead.setNumberOfSubordinates("15");
        validTeamLead.setTelephoneNumber("+71111111111");

        return validTeamLead;
    }

    public static Employee getInvalidTeamLead() {
        Employee invalidTeamLead = getEmployeeWithValidName();
        invalidTeamLead.setPosition("TeamLead");
        invalidTeamLead.setProgrammingLanguage("Java");

        return invalidTeamLead;
    }

    public static Employee getValidManager() {
        Employee validManager = getEmployeeWithValidName();
        validManager.setPosition("Manager");
        validManager.setEmail("test@qwer.ty");
        validManager.setTelephoneNumber("+71111111111");

        return validManager;
    }

    public static Employee getInvalidManager() {
        Employee invalidManager = getEmployeeWithValidName();
        invalidManager.setPosition("Manager");
        invalidManager.setProgrammingLanguage("Java");

        return invalidManager;
    }

    public static Employee getEmployeeWithValidSalary() {
        Employee validEmployee = getValidDeveloper();
        validEmployee.setSalary("65000");

        return validEmployee;
    }

    public static Employee getEmployeeWithInvalidSalary() {
        Employee invalidEmployee = getValidDeveloper();
        invalidEmployee.setSalary("25000");

        return invalidEmployee;
    }

    public static Employee getEmployeeWithValidTask() {
        Task validTask = new Task();
        validTask.setDescription("testTask");

        Employee employee = new Employee();
        employee.getTasks().add(validTask);

        return employee;
    }

    public static Employee getEmployeeWithInvalidTask() {
        Task invalidTask = new Task();

        Employee employee = new Employee();
        employee.getTasks().add(invalidTask);

        return employee;
    }
}
