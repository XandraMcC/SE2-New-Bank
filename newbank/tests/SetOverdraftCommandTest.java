package newbank.tests;

import newbank.server.*;
import newbank.server.commands.SetOverdraftCommand;
import org.junit.jupiter.api.*;

class SetOverdraftCommandTest {

  static double NEW_OVERDRAFT = 100.00;

  Customer customer;
  Account account;
  SetOverdraftCommand command;

  @BeforeEach
  void setUp() {
    customer = new Customer("Bob", "password");
    account = new Account("checking");
    customer.addAccount(account);
    command = new SetOverdraftCommand();
  }

  @Test
  @DisplayName("Run set overdraft command")
  void process() {
    String result = command.process(customer, "checking " + NEW_OVERDRAFT);
    Assertions.assertFalse(result.contains("FAIL"));
    Assertions.assertEquals(account.getOverdraftLimit().getValue(), NEW_OVERDRAFT*100);
  }
}