package newbank.tests;

import newbank.server.*;
import newbank.server.commands.WithdrawCommand;
import org.junit.jupiter.api.*;

class WithdrawCommandTest{

  Customer customer = new Customer("Bob","Bob");
  WithdrawCommand command = new WithdrawCommand();
  Account checking;

  @BeforeEach
  void setUp() {
    checking = new Account("checking", Currency.FromInteger(100));
    customer.addAccount(checking);
  }

  @Test
  void process() {
    String result = command.process(customer, "checking 50");
    Assertions.assertFalse(result.contains("FAIL"));
    Assertions.assertEquals(Currency.FromDouble(50.00).getValue(), checking.getBalance().getValue());
  }
}