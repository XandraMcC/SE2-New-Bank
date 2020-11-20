package newbank.server.commands;

import newbank.server.Customer;

public class WithdrawCommand extends Command {

  public WithdrawCommand() {
    super("WITHDRAW",
            "<account_name> <amount>",
            "");
  }

  @Override
  public String process(Customer customer, String argument) {
    return null;
  }
}
