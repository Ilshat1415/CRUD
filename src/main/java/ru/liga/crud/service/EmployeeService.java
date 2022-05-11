package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.PositionsEnum;
import ru.liga.crud.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        Assert.notNull(employee, "Сотрудник по id = " + id + " не найден");

        return employee;
    }

    public void saveEmployee(Employee employee) {
        Assert.notNull(employee.getFirstName(), "Вы не указали обязательное поле FirstName");
        Assert.notNull(employee.getLastName(), "Вы не указали обязательное поле LastName");
        Assert.notNull(employee.getPosition(), "Вы не указали обязательное поле Position");

        positionCheck(employee);

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
        findById(employee.getId());
        saveEmployee(employee);
    }

    private void positionCheck(Employee employee) {
        String position = employee.getPosition();

        Assert.isTrue(
                Arrays.stream(PositionsEnum.values())
                        .map(PositionsEnum::getPosition)
                        .collect(Collectors.toList())
                        .contains(position),
                "Вы указали не подходящую позицию для сотрудника, доступны следующие позиции:\n" +
                        "Tester, Developer, TeamLead, Manager"
        );

        if (position.equals(PositionsEnum.TESTER.getPosition())) {
            Assert.isTrue(
                    employee.getSalary() > PositionsEnum.TESTER.getSalaryMin() && employee.getSalary() > PositionsEnum.TESTER.getSalaryMax(),
                    "У тестера зарплата должна быть в диапазоне от 25К до 150К, в запросе прислали " + employee.getSalary()
            );
            Assert.isNull(employee.getNumberOfSubordinates(), "У тестера не должно быть подчинённых");
            Assert.isNull(employee.getProgrammingLanguage(), "У тестера не нужно указывать ProgrammingLanguage");

        } else if (position.equals(PositionsEnum.DEVELOPER.getPosition())) {
            Assert.isTrue(
                    employee.getSalary() > PositionsEnum.DEVELOPER.getSalaryMin() && employee.getSalary() < PositionsEnum.DEVELOPER.getSalaryMax(),
                    "У разработчика зарплата должна быть в диапазоне от 40К до 400К, в запросе прислали " + employee.getSalary()
            );
            Assert.notNull(employee.getProgrammingLanguage(), "Вы не указали обязательное поле для разработчика ProgrammingLanguage");
            Assert.isNull(employee.getNumberOfSubordinates(), "У разработчика не должно быть подчинённых");

        } else if (position.equals(PositionsEnum.TEAMLEAD.getPosition())) {
            Assert.isTrue(
                    employee.getSalary() > PositionsEnum.TEAMLEAD.getSalaryMin() && employee.getSalary() < PositionsEnum.TEAMLEAD.getSalaryMax(),
                    "У руководителя зарплата должна быть в диапазоне от 150К до 400К, в запросе прислали " + employee.getSalary()
            );
            Assert.notNull(employee.getTelephoneNumber(), "Вы не указали обязательное поле для руководителя TelephoneNumber");
            Assert.notNull(employee.getNumberOfSubordinates(), "Вы не указали обязательное поле для руководителя NumberOfSubordinates");
            Assert.isNull(employee.getProgrammingLanguage(), "У руководителя не нужно указывать ProgrammingLanguage");

        } else if (position.equals(PositionsEnum.MANAGER.getPosition())) {
            Assert.isTrue(
                    employee.getSalary() > PositionsEnum.MANAGER.getSalaryMin() && employee.getSalary() < PositionsEnum.MANAGER.getSalaryMax(),
                    "У менеджера зарплата должна быть в диапазоне от 180К до 300К, в запросе прислали " + employee.getSalary()
            );
            Assert.notNull(employee.getTelephoneNumber(), "Вы не указали обязательное поле для менеджера TelephoneNumber");
            Assert.notNull(employee.getEmail(), "Вы не указали обязательное поле для менеджера Email");
            Assert.isNull(employee.getProgrammingLanguage(), "У менеджера не нужно указывать ProgrammingLanguage");

        }
    }
}
