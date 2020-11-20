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

    String arguments[] = argument.split(" ");
    if (arguments.length < 2) {
      return "FAIL";
    }
    String accountName = arguments[0];
    if (customer.hasACC(accountName)) {
      return "FAIL";
    }

    Currency openingBalance;
    try {
      openingBalance = new Currency(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL";
    }

    Account account = customer.newACC(accountName, openingBalance);

    return "New account " + account.getAccountName() + " created" +
            " balance: " + account.getBalance();
  }
}
