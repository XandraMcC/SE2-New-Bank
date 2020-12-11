package newbank.server.commands;

import newbank.server.Account;
import newbank.server.Constants;
import newbank.server.Currency;
import newbank.server.Customer;

public class WithdrawCommand extends Command {

  public WithdrawCommand() {
    super("WITHDRAW",
            "<account_name> <amount>",
            "withdraw money from an account");
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 2) {
      return Constants.FAIL;
    }
    Account account = customer.getAccount(arguments[0]);
    if (account == null) {
      return Constants.FAIL;
    }

    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    }
    catch (NumberFormatException e) {
      return Constants.FAIL;
    }

    try {
      account.withdraw(amount);
    } catch (Exception e) {
      return Constants.FAIL;
    }

    return "Withdrew " + amount.toString() +
            " from " + account.getAccountName() +
            ". New balance: " + account.getBalance().toString();
  }
}
