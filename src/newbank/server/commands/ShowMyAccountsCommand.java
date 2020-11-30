package newbank.server.commands;

import newbank.server.Customer;

public class ShowMyAccountsCommand extends Command {

  public ShowMyAccountsCommand() {
    super("SHOWMYACCOUNTS",
            "",
            "");
  }

  @Override
  public String process(Customer customer, String argument) {
    return customer.accountsToString();
  }
}
