package ru.liga.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.crud.api.EmployeeService;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.service.PdfCreationService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final PdfCreationService pdfCreationService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info("GET request received");
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseEmployee> getEmployee(@PathVariable String uuid) {
        log.info("GET request received with parameter = {}", uuid);
        return new ResponseEntity<>(employeeService.findByUuid(uuid), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/download")
    //todo зачем еще доп контроллер, если есть целевой для employee
    // done, удалил его лишний контроллер, туду перес сюда, чтоб не потерялось
    public ResponseEntity<ResponseEmployee> getPdfByUuid(@PathVariable String uuid, HttpServletResponse response) {
        //todo оч странные переносы)) так какого смысла в них нет
        // done
        log.info("GET request received with parameter = {}", uuid);

        ResponseEmployee responseEmployee = employeeService.findByUuid(uuid);
        if (responseEmployee.getEmployee() != null) {
            pdfCreationService.printPdf(responseEmployee.getEmployee(), response);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            return new ResponseEntity<>(headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(responseEmployee, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<List<ResponseEmployee>> addEmployee(@RequestBody List<Employee> employees) {
        log.info("POST request received with parameter = {}", employees);

        List<ResponseEmployee> responseEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            responseEmployees.add(employeeService.saveEmployee(employee));
        }

        return new ResponseEntity<>(responseEmployees, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseEmployee> updateEmployee(@RequestBody Employee employee) {
        log.info("POST request received with parameter = {}", employee);
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @PostMapping("/{uuid}/delete")
    public ResponseEntity<ResponseEmployee> deleteEmployee(@PathVariable String uuid) {
        log.info("POST request received with parameter = {}", uuid);
        return new ResponseEntity<>(employeeService.deleteEmployee(uuid), HttpStatus.OK);
    }
}
