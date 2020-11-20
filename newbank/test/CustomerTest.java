import com.sun.jdi.request.DuplicateRequestException;
import newbank.server.Account;
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
    account1 = new Account(TEST_ACCOUNT1_NAME, TEST_ACCOUNT1_OPENING_BALANCE);
    account2 = new Account(TEST_ACCOUNT2_NAME, TEST_ACCOUNT2_OPENING_BALANCE);
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
    Account account = new Account("test", 666);
    customer.addAccount(account);
    Assertions.assertSame(account, customer.getTransactionAccount("test"));

    // Check the same account can't be added twice
    Assertions.assertThrows(Exception.class, () -> customer.addAccount(account));
  }

  @Test
  void deposit() {

    //Deposit 100 and check the account balance is updated accordingly
    final float amountDeposited = 100;
    customer.Deposit(TEST_ACCOUNT1_NAME, amountDeposited);
    double newBalance = customer.getTransactionAccount(TEST_ACCOUNT1_NAME).getCurrentBalance();
    Assertions.assertEquals(TEST_ACCOUNT1_OPENING_BALANCE + amountDeposited, newBalance);

    //Test no change when depositing nothing
    customer.Deposit(TEST_ACCOUNT2_NAME, 0);
    newBalance  = customer.getTransactionAccount(TEST_ACCOUNT2_NAME).getCurrentBalance();
    Assertions.assertEquals(TEST_ACCOUNT2_OPENING_BALANCE, newBalance);

    //Should not be able to deposit to an invalid account name
    Assertions.assertThrows(Exception.class, () -> customer.Withdraw("Invlaid", 100));

    //Should not be able to deposit negative monies
    Assertions.assertThrows(Exception.class, () -> customer.Deposit(TEST_ACCOUNT1_NAME, -100));

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
    Assertions.assertSame(account1, customer.getTransactionAccount(TEST_ACCOUNT1_NAME));
    Assertions.assertSame(account2, customer.getTransactionAccount(TEST_ACCOUNT2_NAME));
  }

  @Test
  void currentBalance() {
    Assertions.assertTrue(customer.currentBalance(TEST_ACCOUNT1_NAME).contains(String.valueOf(TEST_ACCOUNT1_OPENING_BALANCE)));
    Assertions.assertTrue(customer.currentBalance(TEST_ACCOUNT2_NAME).contains(String.valueOf(TEST_ACCOUNT2_OPENING_BALANCE)));
  }

  @Test
  void withdraw() {

    //Withdraw 100 and check the account balance is updated accordingly
    final float amountWithdrew = 100;
    customer.Withdraw(TEST_ACCOUNT1_NAME, amountWithdrew);
    double newBalance = customer.getTransactionAccount(TEST_ACCOUNT1_NAME).getCurrentBalance();
    Assertions.assertEquals(TEST_ACCOUNT1_OPENING_BALANCE - amountWithdrew, newBalance);

    //Test no change when withdrawing nothing
    customer.Withdraw(TEST_ACCOUNT2_NAME, 0);
    newBalance  = customer.getTransactionAccount(TEST_ACCOUNT2_NAME).getCurrentBalance();
    Assertions.assertEquals(TEST_ACCOUNT2_OPENING_BALANCE, newBalance);

    //Should not be able to withdraw from an invalid account name
    Assertions.assertThrows(Exception.class, () -> customer.Withdraw("Invlaid", 100));

    //Should not be able to withdraw negative monies
    Assertions.assertThrows(Exception.class, () -> customer.Withdraw(TEST_ACCOUNT1_NAME, -100));
  }
}