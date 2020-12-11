package newbank.tests;

import newbank.server.Customer;
import newbank.server.commands.AddAccountCommand;
import org.junit.jupiter.api.*;

class AddAccountCommandTest {
  static String ACCOUNT_NAME = "savings";
  static String ACCOUNT_BALANCE = "100";
  static String COMMAND_PARAMETERS = ACCOUNT_NAME + " " + ACCOUNT_BALANCE;
  static String TEST_CUSTOMER = "Bob";

  AddAccountCommand command;
  Customer customer;

  @BeforeEach
  void setUp() {
    command = new AddAccountCommand();
    customer = new Customer(TEST_CUSTOMER, TEST_CUSTOMER);
  }

  @Test
  void addTestAccount() {
    String result = command.process(customer, COMMAND_PARAMETERS);
    Assertions.assertFalse(result.contains("FAIL"));
    Assertions.assertNotNull(customer.getAccount(ACCOUNT_NAME));
  }

  @Test
  void rejectDuplicateAccount() {
    String result = command.process(customer, COMMAND_PARAMETERS);
    Assertions.assertFalse(result.contains("FAIL"));
    result = command.process(customer, COMMAND_PARAMETERS);
    Assertions.assertTrue(result.contains("FAIL"));
  }

  @Test
  void handleNullCustomer() {
    String result = command.process(null, "");
    Assertions.assertTrue(result.contains("FAIL"));
  }

  @Test
  void handleNoAccountName() {
    String result = command.process(customer, "");
    Assertions.assertTrue(result.contains("FAIL"));
  }

  @Test
  void handleInvalidAccountName() {
    String result = command.process(customer, " ");
    Assertions.assertTrue(result.contains("FAIL"));
  }
}