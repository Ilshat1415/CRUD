package ru.liga.crud.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.exception.InvalidFieldException;

import java.util.ResourceBundle;

@Getter
@RequiredArgsConstructor
public enum PositionsEnum {
    TESTER("Tester", 25000, 150000),
    DEVELOPER("Developer", 40000, 400000),
    TEAM_LEAD("Team_Lead", 150000, 400000),
    MANAGER("Manager", 180000, 300000);

    private final String position;
    private final int salaryMin;
    private final int salaryMax;
    private static final ResourceBundle rb = ResourceBundle.getBundle("text");

    public static PositionsEnum getValue(String position) throws InvalidFieldException {
        if (TESTER.position.equals(position)) {
            return TESTER;
        } else if (DEVELOPER.position.equals(position)) {
            return DEVELOPER;
        } else if (TEAM_LEAD.position.equals(position)) {
            return TEAM_LEAD;
        } else if (MANAGER.position.equals(position)) {
            return MANAGER;
        } else {
            throw new InvalidFieldException(String.format(
                    rb.getString("invalidPosition"),
                    position
            ));
        }
    }
}
