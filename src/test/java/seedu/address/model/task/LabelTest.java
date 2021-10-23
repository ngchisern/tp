package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LabelTest {
    private final String tooLong = "0123456789012345678901234567890123456789012345678901234567890123456789"
            + "0123456789012345678901234567890";
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidLabel = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidLabel));
    }

    @Test
    public void isValidLabel() {
        assertThrows(NullPointerException.class, () -> Label.isValidLabel(null));

        //invalid Labels
        assertFalse(Label.isValidLabel(tooLong)); //label is too long
        assertFalse(Label.isValidLabel(" fly")); //starts with a space

        //valid labels
        assertTrue(Label.isValidLabel("sew buttons on blazer"));
        assertTrue(Label.isValidLabel("sew   buttons on blazer")); //multiple spaces
    }

}
