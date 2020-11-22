package newbank.server.commands;

import newbank.server.*;

public class ShowBalanceCommand extends Command {

  public ShowBalanceCommand() {
    super("SHOWCURRENTBALANCE",
            "<account_name>",
            "Retrieves the balance for specific account");
  }

  @Override
  public String process(Customer customer, String argument) {

    Account account = customer.getAccount(argument);
    if (account == null) {
      return "FAIL";
    }

    return account.getBalance().toString();
  }
}
