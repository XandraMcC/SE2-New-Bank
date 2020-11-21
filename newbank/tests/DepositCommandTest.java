import newbank.server.*;
import newbank.server.commands.DepositCommand;
import org.junit.jupiter.api.*;


class DepositCommandTest {
  static String CUSTOMER_NAME = "Bob";
  static String ACCOUNT_NAME = "checking";
  static float INITIAL_AMOUNT = 100;
  static float DEPOSIT_AMOUNT = 50;
  static String COMMAND_PARAMETER = ACCOUNT_NAME + " " + DEPOSIT_AMOUNT;

  Customer customer;
  Account account;
  DepositCommand command;

  @BeforeEach
  void setUp() {
    customer = new Customer(CUSTOMER_NAME, CUSTOMER_NAME);
    account = new Account(ACCOUNT_NAME, new Currency(INITIAL_AMOUNT));
    customer.addAccount(account);
    command = new DepositCommand();
  }

  @Test
  void testDeposit() {
    String result = command.process(customer, COMMAND_PARAMETER);
    Assertions.assertFalse(result.contains("FAIL"));
    int newBalancePennies = (int)Math.floor(INITIAL_AMOUNT * 100) + (int)Math.floor(DEPOSIT_AMOUNT * 100);
    Assertions.assertEquals(newBalancePennies, account.getBalance().getValue());
  }

  @Test
  void testUnknownAccount() {
    String result = command.process(customer,"error 50");
    Assertions.assertTrue(result.contains("FAIL"));
  }
}