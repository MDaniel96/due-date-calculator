package util;

import java.time.LocalDateTime;

public class TestData {

    LocalDateTime givenSubmitDate;
    int givenTurnaroundHours;

    LocalDateTime expectedDueDate;

    public TestData(LocalDateTime givenSubmitDate, int givenTurnaroundHours, LocalDateTime expectedDueDate) {
        this.givenSubmitDate = givenSubmitDate;
        this.givenTurnaroundHours = givenTurnaroundHours;
        this.expectedDueDate = expectedDueDate;
    }

    public LocalDateTime getGivenSubmitDate() {
        return givenSubmitDate;
    }

    public int getGivenTurnaroundHours() {
        return givenTurnaroundHours;
    }

    public LocalDateTime getExpectedDueDate() {
        return expectedDueDate;
    }
}