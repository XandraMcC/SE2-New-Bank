import newbank.server.Customer;
import newbank.server.commands.ChangePasswordCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordCommandTest {

  static String CUSTOMER_NAME = "Bob";
  static String INITIAL_PASSWORD = "password";
  static String TEST_PASSWORD = "updated";

  ChangePasswordCommand command;
  Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer(CUSTOMER_NAME, INITIAL_PASSWORD);
    command = new ChangePasswordCommand();
  }

  @Test
  void changePassword() {
    String result = command.process(customer, TEST_PASSWORD);
    Assertions.assertFalse(result.contains("FAIL"));
    Assertions.assertEquals(TEST_PASSWORD, customer.getPassword());
  }

  @Test
  void rejectNewPasswordBlank() {
    String result = command.process(customer, "");
    Assertions.assertTrue(result.contains("FAIL"));
    Assertions.assertEquals(INITIAL_PASSWORD, customer.getPassword());
  }
}