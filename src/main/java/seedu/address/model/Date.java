package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should begin with an alphanumeric character, followed by up to 99 more characters that"
            + "are alphanumeric or spaces";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+[ \\p{Alnum}]*";
    public final String parsedDate;

    /**
     * Constructs a {@code Date}
     *
     * @param date A valid date for a task.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        parsedDate = date;
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 100;
    }

    @Override
    public String toString() {
        return parsedDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && parsedDate.equals(((Date) other).parsedDate));
    }

    @Override
    public int hashCode() {
        return parsedDate.hashCode();
    }
}
