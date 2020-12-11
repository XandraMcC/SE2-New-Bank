package newbank.tests;

import newbank.server.*;
import org.junit.jupiter.api.*;
import java.security.InvalidParameterException;

class CustomerTest {

  final String TEST_NAME = "Bob";
  final String TEST_PASSWORD = "password";
  final String TEST_ACCOUNT1_NAME = "savings";
  final int TEST_ACCOUNT1_OPENING_BALANCE = 200;
  final String TEST_ACCOUNT2_NAME = "checking";
  final int TEST_ACCOUNT2_OPENING_BALANCE = 200;

  Customer customer;
  Account account1;
  Account account2;

  @BeforeEach
  void setup() {
    customer = new Customer(TEST_NAME, TEST_PASSWORD);
    account1 = new Account(TEST_ACCOUNT1_NAME, Currency.FromInteger(TEST_ACCOUNT1_OPENING_BALANCE));
    account2 = new Account(TEST_ACCOUNT2_NAME, Currency.FromInteger(TEST_ACCOUNT2_OPENING_BALANCE));
    customer.addAccount(account1);
    customer.addAccount(account2);
  }

  @Test
  void accountsToString() {
    String s = customer.accountsToString();
    Assertions.assertTrue(s.contains(TEST_ACCOUNT1_NAME) &&
            s.contains(TEST_ACCOUNT2_NAME));
  }

  @Test
  void addAccount() {
    Account account = new Account("test", Currency.FromInteger(666));
    customer.addAccount(account);
    Assertions.assertSame(account, customer.getAccount("test"));
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
  void getTransactionAccount() {
    Assertions.assertSame(account1, customer.getAccount(TEST_ACCOUNT1_NAME));
    Assertions.assertSame(account2, customer.getAccount(TEST_ACCOUNT2_NAME));
  }
}