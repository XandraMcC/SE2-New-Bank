import newbank.server.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void testToString() {
    Account account = new Account("test", 350);
    Assertions.assertTrue(account.toString().contains("test") && account.toString().contains("350"));
  }

  @Test
  void getAccountName() {
    Account bobAccount = new Account("Bob", 200);
    Account johnAccount = new Account("John", 350);
    Assertions.assertEquals("Bob", bobAccount.getAccountName());
    Assertions.assertEquals("John", johnAccount.getAccountName());
  }

  @Test
  void getCurrentBalance() {
    Account account1 = new Account("", 100.01);
    Account account2 = new Account("", -99);
    Assertions.assertEquals(account1.getCurrentBalance(), 100.01);
    Assertions.assertEquals(account2.getCurrentBalance(), -99.0);
  }

  @Test
  void setCurrentBalance() {
    Account account = new Account("", 0);
    account.setCurrentBalance(10);
    Assertions.assertEquals(account.getCurrentBalance(), 10);
    account.setCurrentBalance(-10);
    Assertions.assertEquals(account.getCurrentBalance(), -10);
  }
}