package newbank.tests;

import newbank.server.*;
import newbank.server.commands.WithdrawCommand;
import org.junit.jupiter.api.*;

class WithdrawCommandTest{

  Customer customer = new Customer("Bob","Bob");
  WithdrawCommand command = new WithdrawCommand();
  Account checking;
  String result;

  @BeforeEach
  void init() {
    checking = new Account("checking", Currency.FromInteger(100));
    customer.addAccount(checking);
    result = command.process(customer, "checking 50");
  }

  @Test
  void process() {
    Assertions.assertEquals(Currency.FromDouble(50.00).getValue(), checking.getBalance().getValue());
  }

  @Test
  void processDoesNotFail() {
    Assertions.assertFalse(result.contains(Constants.FAIL));
  }
}