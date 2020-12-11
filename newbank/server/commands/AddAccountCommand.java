package newbank.server.commands;

import newbank.server.*;

public class AddAccountCommand extends Command {

  public AddAccountCommand() {
    super("ADDACCOUNT",
            "<account_name> <opening_balance>",
            "opens a new account");
  }

  @Override
  public String process(Customer customer, String argument) {
    Account account;
    String accountName;
    Currency openingBalance;
    String[] arguments = argument.split(" ");

    if (arguments.length < 2) {
      return Constants.FAIL;
    }

    accountName = arguments[0];
    if (customer.hasAccount(accountName)) {
      return Constants.FAILACCOUNTEXISTS;
    }

    try {
      openingBalance = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return Constants.FAIL;
    }

    try {
      account = customer.newAccount(accountName, openingBalance);
    } catch (Exception e) {
      return Constants.FAIL;
    }

    return "New account '" + account.getAccountName() + "' created" +
            ". Balance: " + account.getBalance().toString();
  }
}
