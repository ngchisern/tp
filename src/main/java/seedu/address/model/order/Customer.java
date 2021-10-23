package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Customer related to a Order.
 */
public class Customer {
    public static final String MESSAGE_CONSTRAINTS =
            "Customer name should be non-empty, and contain one or more blocks of alphanumeric characters, "
            + "separated by at most one space";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+( \\p{Alnum}+)*";
    public final String name;

    /**
     * Constructs a {@code Customer}
     *
     * @param name A valid customer name.
     */
    public Customer(String name) {
        requireNonNull(name);
        checkArgument(isValidCustomer(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    public static boolean isValidCustomer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Customer
                && name.equals(((Customer) other).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
