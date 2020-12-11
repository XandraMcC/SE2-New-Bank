package newbank.server.commands;

import newbank.server.*;

public class ShowBalanceCommand extends Command {

  public ShowBalanceCommand() {
    super("SHOWCURRENTBALANCE",
            "<account_name>",
            "retrieve the balance for specific account");
  }

  @Override
  public String process(Customer customer, String argument) {

    Account account = customer.getAccount(argument);
    if (account == null) {
      return Constants.FAIL;
    }

    return "Current balance: " + account.getBalance().toString();
  }
}
