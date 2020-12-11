package newbank.server.commands;

import newbank.server.*;

public class SetOverdraftCommand extends Command {

  public SetOverdraftCommand() {
    super("SETOVERDRAFT",
            "<account_name> <new_overdraft_limit>",
            "set a new overdraft limit on a specified account");
  }

  @Override
  public String process(Customer customer, String argument) {
    if (customer == null) {
      return Constants.FAILNOTLOGGEDIN;
    }
    String[] arguments = argument.split(" ");
    if (arguments.length < 1) {
      return Constants.FAILNOTENOUGHARGS;
    }
    Account account = customer.getAccount(arguments[0]);
    if (account == null) {
      return Constants.FAILACCOUNTNOTFOUND;
    }
    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return Constants.FAILACCOUNTNOTFOUND;
    }
    try {
      account.setOverdraftLimit(amount);
    }
    catch (InsufficientFundsException e) {
      return Constants.FAILINSUFFICIENTFUNDS;
    }
    return "New overdraft limit set: " + account.getOverdraftLimit().toString() + " for " + account.getAccountName();
  }
}
