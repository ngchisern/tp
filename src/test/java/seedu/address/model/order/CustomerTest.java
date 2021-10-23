package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CustomerTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Customer(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidCustomer = "";
        assertThrows(IllegalArgumentException.class, () -> new Customer(invalidCustomer));
    }

    @Test
    public void isValidCustomer() {
        //invalid customers
        assertFalse(Customer.isValidCustomer("")); //empty string
        assertFalse(Customer.isValidCustomer(" ")); //single space
        assertFalse(Customer.isValidCustomer("   ")); //multiple spaces
        assertFalse(Customer.isValidCustomer("John ")); //empty after space
        assertFalse(Customer.isValidCustomer("John@")); //non-alphanumeric
        assertFalse(Customer.isValidCustomer("John doe ")); //empty after second space
        assertFalse(Customer.isValidCustomer(" John doe")); //beginning with a space
        assertFalse(Customer.isValidCustomer("John  doe")); //separated by multiple spaces

        //valid customers
        assertTrue(Customer.isValidCustomer("John the seventh")); //multiple blocks separated by single space
        assertTrue(Customer.isValidCustomer("John doe2")); //allows numbers
    }
}
