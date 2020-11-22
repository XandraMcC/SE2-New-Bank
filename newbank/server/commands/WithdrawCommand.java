package newbank.server.commands;

import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;

public class WithdrawCommand extends Command {

  public WithdrawCommand() {
    super("WITHDRAW",
            "<account_name> <amount>",
            "Withdraw money from an account");
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 2) {
      return "FAIL";
    }
    Account account = customer.getAccount(arguments[0]);
    if (account == null) {
      return "FAIL";
    }

    Currency amount;
    try {
      amount = new Currency(arguments[1]);
    }
    catch (NumberFormatException e) {
      return "FAIL";
    }

    try {
      account.withdraw(amount);
    } catch (Exception e) {
      return "FAIL";
    }

    return "Withdrew " + amount.toString() +
            " from " + account.getAccountName() +
            " new balance " + account.getBalance();
  }
}
