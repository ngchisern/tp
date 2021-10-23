package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTest {
    private final String tooLong = "0123456789012345678901234567890123456789012345678901234567890123456789"
            + "0123456789012345678901234567890";
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidLabel = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidLabel));
    }

    @Test
    public void isValidLabel() {
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        //invalid Labels
        assertFalse(Date.isValidDate(tooLong)); //label is too long
        assertFalse(Date.isValidDate(" 20th august 2020")); //starts with a space

        //valid labels
        assertTrue(Date.isValidDate("20th august 2020"));
        assertTrue(Date.isValidDate("20th    august 2020")); //multiple spaces
    }

}
