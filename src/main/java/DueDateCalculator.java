import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DueDateCalculator {

    private static final int FIRST_WORKING_HOUR = 9;
    private static final int LAST_WORKING_HOUR = 16;

    public LocalDateTime calculateDueDate(LocalDateTime submitDate, int turnaroundHours) {
        validateInputData(submitDate, turnaroundHours);

        LocalDateTime dueDate = submitDate;
        while (turnaroundHours > 0) {
            dueDate = dueDate.plusHours(1);
            if (isInWorkingPeriod(dueDate)) {
                turnaroundHours--;
            }
        }
        return dueDate;
    }

    private void validateInputData(LocalDateTime submitDate, int turnaroundHours) {
        if (submitDate == null || !isInWorkingPeriod(submitDate) || turnaroundHours < 0) {
            throw new IllegalArgumentException("Wrong input data provided");
        }
    }

    private boolean isInWorkingPeriod(LocalDateTime date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                date.getHour() >= FIRST_WORKING_HOUR && date.getHour() <= LAST_WORKING_HOUR;
    }
}