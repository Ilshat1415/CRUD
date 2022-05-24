package ru.liga.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.crud.api.EmployeeService;
import ru.liga.crud.response.ResponseEmployee;
import ru.liga.crud.service.PdfCreationService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/download") //todo зачем еще доп контроллер, если есть целевой для employee
@RequiredArgsConstructor
public class DownloadController {
    private final PdfCreationService pdfCreationService;
    private final EmployeeService employeeService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseEmployee> getPdfByUuid(
            @PathVariable String uuid,
            HttpServletResponse response
    ) throws IOException { //todo оч странные переносы)) так какого смысла в них нет
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
}
