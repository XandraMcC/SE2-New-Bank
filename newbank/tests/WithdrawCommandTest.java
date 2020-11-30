import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;
import newbank.server.commands.WithdrawCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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