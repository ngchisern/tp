package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        //invalid amounts
        assertFalse(Amount.isValidAmount(" ")); //whitespaces
        assertFalse(Amount.isValidAmount("abc")); //letters
        assertFalse(Amount.isValidAmount("923abc")); //mixed characters
        assertFalse(Amount.isValidAmount("12.12.12")); //multiple dots
        assertFalse(Amount.isValidAmount("12.")); //empty after the dot
        assertFalse(Amount.isValidAmount(".12")); //empty before the dot
        assertFalse(Amount.isValidAmount("123.456")); //too many numbers after the dot

        //valid amounts
        assertTrue(Amount.isValidAmount("12")); //small amounts
        assertTrue(Amount.isValidAmount("1")); //single digit
        assertTrue(Amount.isValidAmount("123412351235125215")); //large amounts
        assertTrue(Amount.isValidAmount("123.45")); //amount with cents
    }
}
