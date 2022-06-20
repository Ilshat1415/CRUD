package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.api.EmployeeService;
import ru.liga.crud.api.ValidatorService;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.repository.EmployeeRepository;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.service.kafka.ProducerService;
import ru.liga.crud.type.Status;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ResourceBundleService resourceBundleService = new ResourceBundleService();
    private final EmployeeRepository employeeRepository;
    private final ValidatorService validatorService;
    private final ProducerService producerService;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseEmployee findByUuid(String uuid) {
        if (employeeRepository.existsByUuid(uuid)) {
            log.debug("Employee with uuid = {} found", uuid);
            return ResponseEmployee.builder()
                    .status(Status.SUCCESS.name())
                    .message("Employee found")
                    .employee(employeeRepository.findByUuid(uuid))
                    .build();
        } else {
            log.debug("Employee with uuid = {} not found", uuid);
            return ResponseEmployee.builder()
                    .status(Status.PROBLEM.name())
                    .message(String.format(resourceBundleService.getMessage("invalidUuid"), uuid))
                    .build();
        }
    }

    @Override
    public ResponseEmployee saveEmployee(Employee employee) {
        ResponseEmployee responseEmployee = validatorService.validate(employee);
        if (employee.isValid()) {
            employee.setUuid(UUID.randomUUID().toString());
            producerService.produce(employee);
            log.debug("Employee with id = {} added", employee.getId());
        }
        return responseEmployee;
    }

    @Override
    public ResponseEmployee updateEmployee(Employee employee) {
        ResponseEmployee responseEmployee = findByUuid(employee.getUuid());

        if (responseEmployee.getEmployee() == null) {
            return responseEmployee;
        } else {
            employee.setId(responseEmployee.getEmployee().getId());
        }

        responseEmployee = validatorService.validate(employee);

        if (employee.isValid()) {
            employeeRepository.save(employee);
            responseEmployee.setMessage("Employee updated");
            log.debug("Employee with uuid = {} updated", employee.getUuid());
        }

        return responseEmployee;
    }

    @Override
    public ResponseEmployee deleteEmployee(String uuid) {
        ResponseEmployee responseEmployee = findByUuid(uuid);

        if (responseEmployee.getEmployee() == null) {
            return responseEmployee;
        }

        employeeRepository.deleteById(findByUuid(uuid).getEmployee().getId());
        responseEmployee.setMessage("Employee removed");

        log.debug("Employee with uuid = {} removed", uuid);

        return responseEmployee;
    }
}
