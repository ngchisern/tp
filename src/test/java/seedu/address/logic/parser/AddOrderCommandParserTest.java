package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.CUSTOMER_DESC_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.CUSTOMER_DESC_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_OCT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CUSTOMER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CUSTOMER_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.Date;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;

public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        Order expectedOrder = new Order(new Customer(VALID_CUSTOMER_SALE1),
                new Date(VALID_DATE_SEPT), new Amount(VALID_AMOUNT_SALE1));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CUSTOMER_DESC_SALE1 + DATE_DESC_SEPT + AMOUNT_DESC_SALE1,
                new AddOrderCommand(expectedOrder));

        // multiple customers - last customer accepted
        assertParseSuccess(parser, CUSTOMER_DESC_SALE2 + CUSTOMER_DESC_SALE1 + DATE_DESC_SEPT + AMOUNT_DESC_SALE1,
                new AddOrderCommand(expectedOrder));

        // multiple dates - last date accepted
        assertParseSuccess(parser, CUSTOMER_DESC_SALE1 + DATE_DESC_OCT + DATE_DESC_SEPT + AMOUNT_DESC_SALE1,
                new AddOrderCommand(expectedOrder));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, CUSTOMER_DESC_SALE1 + DATE_DESC_SEPT + AMOUNT_DESC_SALE2 + AMOUNT_DESC_SALE1,
                new AddOrderCommand(expectedOrder));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing customer prefix
        assertParseFailure(parser, VALID_CUSTOMER_SALE1 + DATE_DESC_SEPT + AMOUNT_DESC_SALE1, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, CUSTOMER_DESC_SALE1 + VALID_DATE_SEPT + AMOUNT_DESC_SALE1, expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, CUSTOMER_DESC_SALE1 + DATE_DESC_SEPT + VALID_AMOUNT_SALE1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid customer
        assertParseFailure(parser, INVALID_CUSTOMER_DESC + DATE_DESC_SEPT + AMOUNT_DESC_SALE1,
                Customer.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, CUSTOMER_DESC_SALE1 + INVALID_DATE_DESC + AMOUNT_DESC_SALE1,
                Date.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, CUSTOMER_DESC_SALE1 + DATE_DESC_SEPT + INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS);
    }
}
