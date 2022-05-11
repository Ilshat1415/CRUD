package ru.liga.crud.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PositionsEnum {
    TESTER("Tester", 25000, 150000),
    DEVELOPER("Developer", 40000, 400000),
    TEAMLEAD("TeamLead", 150000, 400000),
    MANAGER("Manager", 180000, 300000);

    private final String position;
    private final int salaryMin;
    private final int salaryMax;
}
