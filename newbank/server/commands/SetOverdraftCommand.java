package newbank.server.commands;

import newbank.server.Account;
import newbank.server.Currency;
import newbank.server.Customer;
import newbank.server.InsufficientFundsException;

public class SetOverdraftCommand extends Command {

  public SetOverdraftCommand() {
    super("SETOVERDRAFT",
            "<account> <new-overdraft-limit>",
            "sets the overdraft on an account");
  }

  @Override
  public String process(Customer customer, String argument) {
    if (customer == null) {
      return "FAIL not logged in";
    }
    String[] arguments = argument.split(" ");
    if (arguments.length < 1) {
      return "FAIL Not enough arguments entered";
    }
    Account account = customer.getAccount(arguments[0]);
    if (account == null) {
      return "FAIL account not found!";
    }
    Currency amount;
    try {
      amount = new Currency(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL amount not recognised";
    }
    try {
      account.setOverdraftLimit(amount);
    }
    catch (InsufficientFundsException e) {
      return "FAIL not enough funds to set overdraft limit";
    }
    return "New overdraft limit set " + account.getOverdraftLimit() + " for " + account.getAccountName();
  }
}
