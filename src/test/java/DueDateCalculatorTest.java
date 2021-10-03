import org.junit.Before;
import org.junit.Test;
import util.TestData;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DueDateCalculatorTest {

    DueDateCalculator dueDateCalculator;

    @Before
    public void setUp() {
        dueDateCalculator = new DueDateCalculator();
    }

    @Test
    public void shouldThrowExceptionWhenDateIsOutOfWorkingHours() {
        // given
        LocalDateTime submitDate = LocalDateTime.of(2021, 7, 8, 8, 30);

        // then
        assertThrows(IllegalArgumentException.class, () ->
                // when
                dueDateCalculator.calculateDueDate(submitDate, 10));
    }

    @Test
    public void shouldThrowExceptionWhenDateIsOnHoliday() {
        // given
        LocalDateTime submitDate = LocalDateTime.of(2021, 7, 10, 9, 30);

        // then
        assertThrows(IllegalArgumentException.class, () ->
                // when
                dueDateCalculator.calculateDueDate(submitDate, 10));
    }

    @Test
    public void shouldThrowExceptionWhenTurnaroundHoursAreNegative() {
        // given
        LocalDateTime submitDate = LocalDateTime.of(2021, 7, 8, 11, 12);

        // then
        assertThrows(IllegalArgumentException.class, () ->
                // when
                dueDateCalculator.calculateDueDate(submitDate, -10));
    }

    @Test
    public void shouldCalculateDueDateForValidInputs() {
        Arrays.asList(
                // given
                new TestData(LocalDateTime.of(2021, 7, 8, 11, 12), 0, LocalDateTime.of(2021, 7, 8, 11, 12)),
                new TestData(LocalDateTime.of(2021, 7, 8, 11, 12), 3, LocalDateTime.of(2021, 7, 8, 14, 12)),
                new TestData(LocalDateTime.of(2021, 7, 8, 11, 12), 10, LocalDateTime.of(2021, 7, 9, 13, 12)),
                new TestData(LocalDateTime.of(2021, 7, 8, 11, 12), 17, LocalDateTime.of(2021, 7, 12, 12, 12))
        ).forEach(testData -> {

            // when
            LocalDateTime dueDate = dueDateCalculator.calculateDueDate(testData.getGivenSubmitDate(), testData.getGivenTurnaroundHours());

            // then
            assertEquals(testData.getExpectedDueDate(), dueDate);
        });
    }
}
