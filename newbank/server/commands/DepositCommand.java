package newbank.server.commands;

import newbank.server.*;

public class DepositCommand extends Command {

  public DepositCommand() {
    super("DEPOSIT",
            "<account> <amount>",
            "Deposit into an account");
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 2) {
      return "FAIL";
    }

    String accountName = arguments[0];

    Account account = customer.getAccount(accountName);
    if (account == null) {
      return "FAIL";
    }

    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL";
    }
    if (amount.isNegative()) {
      return "FAIL";
    }

    account.deposit(amount);

    return "Deposited " + amount.toString() +
            " into " + account.getAccountName() +
            " new balance " + account.getBalance().toString();
  }
}
