package newbank.tests;

import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;
import newbank.server.InsufficientFundsException;
import newbank.server.commands.DepositCommand;
import newbank.server.commands.WithdrawCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TransactionHistoryCommandTest {

  private Customer customer = new Customer("Bob","Bob");
  private WithdrawCommand withdrawCommand = new WithdrawCommand();
  private DepositCommand depositCommand = new DepositCommand();
  private Account account;
  private ArrayList<String> result;

  @BeforeEach
  void init() {
    account = new Account("checking", Currency.FromInteger(100));
    customer.addAccount(account);
    depositCommand.process(customer, "checking 1000");
    withdrawCommand.process(customer, "checking 50");
    result = new ArrayList<String>();
    result.add("Deposited: £1000.00");
    result.add("Withdrew: £50.00");
  }

  @Test
  void testGetTransactionHistory() {
    Assertions.assertEquals(result, account.getTransactionHistory());
  }

}