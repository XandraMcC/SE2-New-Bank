import newbank.server.Account;
import newbank.server.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void testToString() {
    Account account = new Account("test", new Currency(350));
    Assertions.assertTrue(account.toString().contains("test") && account.toString().contains("350"));
  }

  @Test
  void getAccountName() {
    Account bobAccount = new Account("Bob", new Currency(200));
    Account johnAccount = new Account("John", new Currency(350));
    Assertions.assertEquals("Bob", bobAccount.getAccountName());
    Assertions.assertEquals("John", johnAccount.getAccountName());
  }

  @Test
  void getCurrentBalance() {
    Account account1 = new Account("", new Currency(100.01));
    Account account2 = new Account("", new Currency(-99));
    Assertions.assertEquals(account1.getBalance(), 100.01);
    Assertions.assertEquals(account2.getBalance(), -99.0);
  }

  @Test
  void setCurrentBalance() {
    Account account = new Account("", new Currency(0));
    account.setBalance(new Currency(10));
    Assertions.assertEquals(account.getBalance(), new Currency(10));
    account.setBalance(new Currency(-10));
    Assertions.assertEquals(account.getBalance(), new Currency(-10));
  }
}