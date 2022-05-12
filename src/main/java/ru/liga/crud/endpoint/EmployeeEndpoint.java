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
    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_PROBLEM = "PROBLEM";

    private final EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();

        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);

        try {
            employeeService.saveEmployee(employee);

            serviceStatus.setStatus(STATUS_SUCCESS);
            serviceStatus.setMessage("Employee added");

        } catch (IllegalArgumentException e) {
            serviceStatus.setStatus(STATUS_PROBLEM);
            serviceStatus.setMessage(e.getMessage());
        }

        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        ServiceStatus serviceStatus = new ServiceStatus();

        try {
            BeanUtils.copyProperties(employeeService.findById(request.getId()), employeeInfo);
            response.setEmployeeInfo(employeeInfo);

            serviceStatus.setStatus(STATUS_SUCCESS);
            serviceStatus.setMessage("Employee found");

        } catch (IllegalArgumentException e) {
            serviceStatus.setStatus(STATUS_PROBLEM);
            serviceStatus.setMessage(e.getMessage());
        }

        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);

        try {
            employeeService.updateEmployee(employee);
            response.setEmployeeInfo(request.getEmployeeInfo());

            serviceStatus.setStatus(STATUS_SUCCESS);
            serviceStatus.setMessage("Employee updated");

        } catch (IllegalArgumentException e) {
            serviceStatus.setStatus(STATUS_PROBLEM);
            serviceStatus.setMessage(e.getMessage());
        }

        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        try {
            employeeService.deleteEmployee(request.getId());

            serviceStatus.setStatus(STATUS_SUCCESS);
            serviceStatus.setMessage("Employee removed");

        } catch (IllegalArgumentException e) {
            serviceStatus.setStatus(STATUS_PROBLEM);
            serviceStatus.setMessage(e.getMessage());
        }

        response.setServiceStatus(serviceStatus);

        return response;
    }
}
