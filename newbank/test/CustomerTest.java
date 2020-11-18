import com.sun.jdi.request.DuplicateRequestException;
import newbank.server.Account;
import newbank.server.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  Customer customer;

  @BeforeEach
  void setup() {
    customer = new Customer("Bob", "password");
    customer.addAccount(new Account("savings", 200));
    customer.addAccount(new Account("checking", 100));
  }

  @Test
  void accountsToString() {
    String s = customer.accountsToString();
    Assertions.assertTrue(s.contains("savings") && s.contains("checking"));
  }

  @Test
  void currentBalanceToString() {
    Assertions.assertEquals("savings: 200.0", customer.CurrentBalanceToString("savings"));
    Assertions.assertEquals("Error No Account Found", customer.CurrentBalanceToString("invalid"));
  }

  @Test
  void addAccount() {
    Account account = new Account("test", 666);
    customer.addAccount(account);
    Assertions.assertSame(account, customer.getTransactionAccount("test"));

    // Check the same account can't be added twice
    Assertions.assertThrows(Exception.class, () -> customer.addAccount(account));
  }

  @Test
  void deposit() {
  }

  @Test
  void updatePassword() {
  }

  @Test
  void getName() {
  }

  @Test
  void getPassword() {
  }

  @Test
  void updateAccount() {
  }

  @Test
  void getTransactionAccount() {
  }

  @Test
  void currentBalance() {
  }

  @Test
  void withdraw() {
  }
}