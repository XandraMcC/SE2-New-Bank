package newbank.server.commands;

import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;
import newbank.server.InsufficientFundsException;

public class SetOverdraftCommand extends Command {

  public SetOverdraftCommand() {
    super("SETOVERDRAFT",
            "<account_name> <new_overdraft_limit>",
            "sets the overdraft on an account");
  }

  @Override
  public String process(Customer customer, String argument) {
    if (customer == null) {
      return "FAIL - Not logged in.";
    }
    String[] arguments = argument.split(" ");
    if (arguments.length < 1) {
      return "FAIL - Not enough arguments entered.";
    }
    Account account = customer.getAccount(arguments[0]);
    if (account == null) {
      return "FAIL - Account name not found.";
    }
    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL - Amount not recognised.";
    }
    try {
      account.setOverdraftLimit(amount);
    }
    catch (InsufficientFundsException e) {
      return "FAIL - Not enough funds to set overdraft limit.";
    }
    return "New overdraft limit set: " + account.getOverdraftLimit().toString() + " for " + account.getAccountName();
  }
}
