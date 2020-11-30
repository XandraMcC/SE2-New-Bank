import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.InsufficientFundsException;
import org.junit.jupiter.api.*;

class AccountTest {

  private Account account;
  private Account account2;
  private Currency currency;
  private Currency currency2;

  @BeforeEach
  void init() {
    account = new Account("Bob", new Currency(200));
    account2 = new Account("Barry", new Currency(0));
    currency = new Currency(10);
    currency2 = new Currency(10);
  }

  @Test
  void testToString() {
    Assertions.assertTrue(account.toString().contains("Bob") && account.toString().contains("200"));
  }

  @Test
  void testGetAccountName() {
    Assertions.assertEquals("Bob", account.getAccountName());
  }

  @Test
  @DisplayName("getBalance() handles negative numbers")
  void testGetBalance1() {
    Account account = new Account("checking", new Currency(-99), new Currency(100));
    Assertions.assertEquals(account.getBalance().toString(), "-£99.00");
  }

  @Test
  @DisplayName("getBalance() handles pennies")
  void testGetBalance2() {
    Account account = new Account("checking", new Currency(100.01));
    Assertions.assertEquals(account.getBalance().toString(), "£100.01");
  }

  @Test
  @DisplayName("setBalance() handles positive numbers")
  void testSetBalance1() {
    account2.setBalance(currency);
    Assertions.assertEquals(account2.getBalance(), currency);
  }

  @Test
  @DisplayName("setBalance() handles negative numbers")
  void testSetBalance2() {
    account2.setBalance(currency2);
    Assertions.assertEquals(account2.getBalance(), currency2);
  }

  @Test
  @DisplayName("setOverdraftLimit & getOverdraftLimit")
  void testSetOverdraft() {
    Currency limitSet = new Currency(200);
    try {
      account.setOverdraftLimit(limitSet);
    } catch (Exception e) {
      Assertions.fail(e);
    }
    Currency limitRead = account.getOverdraftLimit();
    Assertions.assertEquals(limitSet.getValue(), limitRead.getValue());
  }

  @Test
  @DisplayName("Cannot withdraw more than overdraft limit")
  void testOverdrawn() {
    Currency limitSet = new Currency(200);
    account.setBalance( new Currency(0));
    try {
      account.setOverdraftLimit(limitSet);
    } catch (Exception e) {
      Assertions.fail(e);
    }
    Assertions.assertThrows(InsufficientFundsException.class, () -> account.withdraw(new Currency(201)));
    Currency limitRead = account.getOverdraftLimit();
    Assertions.assertEquals(0, account.getBalance().getValue());
  }

}