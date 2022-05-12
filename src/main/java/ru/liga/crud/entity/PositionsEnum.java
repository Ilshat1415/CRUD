package ru.liga.crud.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    public static PositionsEnum getValue(String position) throws IllegalArgumentException {
        if (TESTER.position.equals(position)) {
            return TESTER;
        } else if (DEVELOPER.position.equals(position)) {
            return DEVELOPER;
        } else if (TEAM_LEAD.position.equals(position)) {
            return TEAM_LEAD;
        } else if (MANAGER.position.equals(position)) {
            return MANAGER;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Position %s is not supported, available positions are: Tester, Developer, TeamLead, Manager",
                    position
            ));
        }
    }
}
