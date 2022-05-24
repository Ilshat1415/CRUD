package ru.liga.crud.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
public class PdfCreationService {
    private static final int FONT_SIZE_BIG = 32;

    public void printPdf(Employee employee, HttpServletResponse response) throws IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            document.add(createParagraph());
            document.add(createList(employee));

        } catch (DocumentException e) {
            log.error(e.getMessage(), e);

        } finally {
            document.close();
        }
    }

    private Paragraph createParagraph() {
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, FONT_SIZE_BIG, Font.BOLD);

        Paragraph title = new Paragraph("Employee", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);

        title.setSpacingAfter(FONT_SIZE_BIG);

        return title;
    }

    private List createList(Employee employee) {
        List list = new List();

        list.add(createListItem("UUID: ", employee.getUuid()));
        list.add(createListItem("FirstName: ", employee.getFirstName()));
        list.add(createListItem("LastName: ", employee.getLastName()));
        list.add(createListItem("Position: ", employee.getPosition()));
        list.add(createListItem("Salary: ", employee.getSalary()));
        list.add(createListItem("TelephoneNumber: ", employee.getTelephoneNumber()));
        list.add(createListItem("ProgrammingLanguage: ", employee.getProgrammingLanguage()));
        list.add(createListItem("NumberOfSubordinates: ", employee.getNumberOfSubordinates()));
        list.add(createListItem("Email: ", employee.getEmail()));

        return list;
    }

    private ListItem createListItem(String fieldName, String fieldValue) {
        ListItem listItem = new ListItem();

        Chunk chunk = new Chunk(fieldName);

        listItem.setListSymbol(chunk);
        listItem.add(fieldValue);

        return listItem;
    }
}
