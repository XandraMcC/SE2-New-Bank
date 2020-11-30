package newbank.server.commands;

import newbank.server.*;

public class AddAccountCommand extends Command {

  public AddAccountCommand() {
    super("ADDACCOUNT",
            "<account_name> <opening balance>",
            "Opens a new account");
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 2) {
      return "FAIL";
    }
    String accountName = arguments[0];
    if (customer.hasAccount(accountName)) {
      return "FAIL";
    }

    Currency openingBalance;
    try {
      openingBalance = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL";
    }

    Account account;
    try {
      account = customer.newAccount(accountName, openingBalance);
    } catch (Exception e) {
      return "FAIL";
    }

    return "New account " + account.getAccountName() + " created" +
            " balance: " + account.getBalance();
  }
}
