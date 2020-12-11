package newbank.server.commands;

import newbank.server.*;

public class DepositCommand extends Command {

  public DepositCommand() {
    super("DEPOSIT",
            "<account_name> <amount>",
            "deposits money into a specific account");
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 2) {
      return Constants.FAILNOTENOUGHARGS;
    }

    String accountName = arguments[0];
    Account account = customer.getAccount(accountName);
    if (account == null) {
      return Constants.FAILACCOUNTNOTFOUND;
    }

    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return Constants.FAIL;
    }

    if (amount.isNegative()) {
      return Constants.FAIL;
    }

    account.deposit(amount);

    return "Deposited " + amount.toString() +
            " into " + account.getAccountName() +
            ". New balance: " + account.getBalance().toString();
  }
}
