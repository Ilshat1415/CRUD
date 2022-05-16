package ru.liga.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.crud.api.EmployeeService;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.exception.ValidationException;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.type.Status;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info("GET request received");
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmployee> getEmployee(@PathVariable Long id) {
        log.info("GET request received with parameter = {}", id);

        ResponseEmployee responseEmployee = new ResponseEmployee();
        try {
            responseEmployee.setEmployee(employeeService.findById(id));
            responseEmployee.setStatus(Status.SUCCESS.name());
            responseEmployee.setMessage("Employee found");

            log.debug("Employee with id = {} found", id);

        } catch (ValidationException e) {
            responseEmployee.setStatus(Status.PROBLEM.name());
            responseEmployee.setMessage(e.getMessage());

            log.debug("Exception {}. Attempt to find an employee. Message: {}",
                    e, e.getMessage());
        }

        return new ResponseEntity<>(responseEmployee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<ResponseEmployee>> addEmployee(@RequestBody List<Employee> employees) {
        log.info("POST request received with parameter = {}", employees);

        List<ResponseEmployee> responseEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            ResponseEmployee responseEmployee = new ResponseEmployee();
            try {
                responseEmployee.setEmployee(employeeService.saveEmployee(employee));
                responseEmployee.setStatus(Status.SUCCESS.name());
                responseEmployee.setMessage("Employee added");

                log.debug("Employee with id = {} added", employee.getId());

            } catch (ValidationException e) {
                responseEmployee.setStatus(Status.PROBLEM.name());
                responseEmployee.setMessage(e.getMessage());

                log.debug("Exception {}. Attempt to create an employee. Message: {}",
                        e, e.getMessage());
            }
            responseEmployees.add(responseEmployee);
        }

        return new ResponseEntity<>(responseEmployees, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseEmployee> updateEmployee(@RequestBody Employee employee) {
        log.info("POST request received with parameter = {}", employee);

        ResponseEmployee responseEmployee = new ResponseEmployee();
        try {
            responseEmployee.setEmployee(employeeService.updateEmployee(employee));
            responseEmployee.setStatus(Status.SUCCESS.name());
            responseEmployee.setMessage("Employee updated");

            log.debug("Employee with id = {} updated", employee.getId());

        } catch (ValidationException e) {
            responseEmployee.setStatus(Status.PROBLEM.name());
            responseEmployee.setMessage(e.getMessage());

            log.debug("Exception {}. Attempt to update an employee. Message: {}",
                    e, e.getMessage());
        }
        return new ResponseEntity<>(responseEmployee, HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<ResponseEmployee> deleteEmployee(@PathVariable Long id) {
        log.info("POST request received with parameter = {}", id);

        ResponseEmployee responseEmployee = new ResponseEmployee();
        try {
            employeeService.deleteEmployee(id);

            responseEmployee.setStatus(Status.SUCCESS.name());
            responseEmployee.setMessage("Employee removed");

            log.debug("Employee with id = {} removed", id);

        } catch (ValidationException e) {
            responseEmployee.setStatus(Status.PROBLEM.name());
            responseEmployee.setMessage(e.getMessage());

            log.debug("Exception {}. Attempt to delete an employee. Message: {}",
                    e, e.getMessage());
        }
        return new ResponseEntity<>(responseEmployee, HttpStatus.OK);
    }
}
