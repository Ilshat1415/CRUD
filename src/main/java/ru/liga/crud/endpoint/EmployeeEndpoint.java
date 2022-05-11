package ru.liga.crud.endpoint;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.service.EmployeeService;
import ru.soap.interfaces.*;


@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://interfaces.soap.ru";

    private final EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        employeeService.saveEmployee(employee);

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Сотрудник успешно добавлен");

        AddEmployeeResponse response = new AddEmployeeResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest request) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeService.findById(request.getId()), employeeInfo);

        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        response.setEmployeeInfo(employeeInfo);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        employeeService.updateEmployee(employee);

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Сотрудник успешно обновлён");

        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        response.setEmployeeInfo(request.getEmployeeInfo());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        employeeService.deleteEmployee(request.getId());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Сотрудник успешно удалён");

        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }
}
