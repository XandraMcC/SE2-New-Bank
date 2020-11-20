package newbank.server.commands;

import newbank.server.Customer;

public class ShowBalanceCommand extends Command {

  public ShowBalanceCommand() {
    super("SHOWCURRENTBALANCE",
            "<account_name>",
            "");
  }

  @Override
  public String process(Customer customer, String argument) {
    return null;
  }
}
