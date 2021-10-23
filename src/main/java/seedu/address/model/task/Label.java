package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's label in the application
 */
public class Label {
    public static final String MESSAGE_CONSTRAINTS =
            "Label should begin with an alphanumeric character, followed by up to 99 more characters that"
            + "are alphanumeric or spaces";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+[ \\p{Alnum}]*";

    public final String checkedLabel;

    /**
     * Constructs a {@code Label}.
     *
     * @param label A valid label for a task.
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        checkedLabel = label;
    }

    /**
     * Returns true if a given string is a valid label.
     *
     * @param test Label to be checked
     */
    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 100;
    }

    @Override
    public String toString() {
        return checkedLabel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Label
                && checkedLabel.equals(((Label) other).checkedLabel));
    }

    @Override
    public int hashCode() {
        return checkedLabel.hashCode();
    }
}
