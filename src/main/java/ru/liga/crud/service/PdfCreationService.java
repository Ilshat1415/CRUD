package ru.liga.crud.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.entity.Task;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Service
public class PdfCreationService {
    private static final int FONT_SIZE_BIG = 32;
    private static final String FONT_PATH = "/font/times.ttf";
    private final Font fontTitle;
    private final Font fontTasks;
    private final Font fontFields;

    public PdfCreationService() {
        fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, FONT_SIZE_BIG, Font.BOLD);
        fontTasks = new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE);

        BaseFont baseFont = getBaseFont();
        if (baseFont != null) {
            fontFields = new Font(baseFont, Font.DEFAULTSIZE, Font.NORMAL);
        } else {
            fontFields = null;
        }
    }

    public void printPdf(Employee employee, HttpServletResponse response) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            document.add(createParagraph());
            document.add(createListForEmployee(employee));

            if (!employee.getTasks().isEmpty()) {
                document.add(new Paragraph("Tasks: ", fontTasks));
                document.add(createTasksList(employee));
            }
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            document.close();
            log.info("PDF {} has been printed", document);
        }
    }

    private BaseFont getBaseFont() {
        try {
            URL fullFontPath = getClass().getResource(FONT_PATH);

            if (fullFontPath != null) {
                return BaseFont.createFont(fullFontPath.toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } else {
                log.warn("Font {} not found, cyrillic support is not initialized", FONT_PATH);
            }
        } catch (DocumentException | IOException e) {
            log.error("Creating a new font. Message {}", e.getMessage(), e);
        }
        return null;
    }

    private Paragraph createParagraph() {
        Paragraph title = new Paragraph("Employee", fontTitle);

        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(FONT_SIZE_BIG);

        return title;
    }

    private List createListForEmployee(Employee employee) {
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

    private List createTasksList(Employee employee) {
        List tasksList = new List();
        for (Task task : employee.getTasks()) {
            tasksList.add(createListItem("UUID: ", task.getUuid()));
            tasksList.add(createListItem("Description: ", task.getDescription()));
        }
        return tasksList;
    }

    private ListItem createListItem(String fieldName, String fieldValue) {
        ListItem listItem = new ListItem();

        if (fontFields != null) {
            listItem.setFont(fontFields);
        }

        listItem.setListSymbol(new Chunk(fieldName));
        listItem.add(fieldValue);

        return listItem;
    }
}
