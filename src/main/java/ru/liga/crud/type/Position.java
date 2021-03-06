package ru.liga.crud.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.liga.crud.entity.Employee;
import ru.liga.crud.service.ResourceBundleService;

@Getter
@RequiredArgsConstructor
public enum Position {
    TESTER("Tester", 25000, 150000, 3),
    DEVELOPER("Developer", 40000, 400000, 3),
    TEAM_LEAD("TeamLead", 150000, 400000, 5),
    MANAGER("Manager", 180000, 300000, 5);

    private final String position;
    private final int salaryMin;
    private final int salaryMax;
    private final int numberTasksMax;

    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();

    public static Position getValue(Employee employee) {
        String positionName = employee.getPosition();

        Position position = getPositionByName(positionName);
        if (position != null) {
            return position;
        } else {
            setMessageForPosition(employee, positionName);
            return null;
        }
    }

    private static void setMessageForPosition(Employee employee, String positionName) {
        if (positionName == null) {
            employee.setPosition(resourceBundleService.getMessage("fieldIsNull"));
        } else {
            employee.setPosition(String.format(
                    resourceBundleService.getMessage("invalidPosition"),
                    positionName));
        }
    }

    private static Position getPositionByName(String position) {
        if (TESTER.position.equals(position)) {
            return TESTER;
        } else if (DEVELOPER.position.equals(position)) {
            return DEVELOPER;
        } else if (TEAM_LEAD.position.equals(position)) {
            return TEAM_LEAD;
        } else if (MANAGER.position.equals(position)) {
            return MANAGER;
        }
        return null;
    }
}
