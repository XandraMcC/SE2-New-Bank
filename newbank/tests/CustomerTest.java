import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

class CustomerTest {

  final String TEST_NAME = "Bob";
  final String TEST_PASSWORD = "password";
  final String TEST_ACCOUNT1_NAME = "savings";
  final double TEST_ACCOUNT1_OPENING_BALANCE = 200;
  final String TEST_ACCOUNT2_NAME = "checking";
  final double TEST_ACCOUNT2_OPENING_BALANCE = 200;

  Customer customer;
  Account account1;
  Account account2;

  @BeforeEach
  void setup() {
    customer = new Customer(TEST_NAME, TEST_PASSWORD);
    account1 = new Account(TEST_ACCOUNT1_NAME, new Currency(TEST_ACCOUNT1_OPENING_BALANCE));
    account2 = new Account(TEST_ACCOUNT2_NAME, new Currency(TEST_ACCOUNT2_OPENING_BALANCE));
    customer.addAccount(account1);
    customer.addAccount(account2);
  }

  @Test
  void accountsToString() {
    String s = customer.accountsToString();
    Assertions.assertTrue(s.contains(TEST_ACCOUNT1_NAME) &&
            s.contains(TEST_ACCOUNT2_NAME));
    Assertions.assertTrue(s.contains(String.valueOf(TEST_ACCOUNT1_OPENING_BALANCE)) &&
            s.contains(String.valueOf(TEST_ACCOUNT2_OPENING_BALANCE)));
  }

  @Test
  void addAccount() {
    Account account = new Account("test", new Currency(666));
    customer.addAccount(account);
    Assertions.assertSame(account, customer.getAccount("test"));

    // Check the same account can't be added twice
    Assertions.assertThrows(Exception.class, () -> customer.addAccount(account));
  }

  @Test
  void updatePassword() {

    //Test too short passwords are rejected
    Assertions.assertThrows(InvalidParameterException.class, () -> customer.updatePassword("1"));

    //Test intended function
    final String newPassword = "okayPassword";
    customer.updatePassword(newPassword);
    Assertions.assertEquals(newPassword, customer.getPassword());

  }

  @Test
  void getName() {
    Assertions.assertEquals(TEST_NAME, customer.getName());
  }

  @Test
  void getPassword() {
    Assertions.assertEquals(TEST_PASSWORD, customer.getPassword());
  }

  @Test
  void updateAccount() {
    Assertions.fail("Don't think this function is needed");
  }

  @Test
  void getTransactionAccount() {
    Assertions.assertSame(account1, customer.getAccount(TEST_ACCOUNT1_NAME));
    Assertions.assertSame(account2, customer.getAccount(TEST_ACCOUNT2_NAME));
  }
}