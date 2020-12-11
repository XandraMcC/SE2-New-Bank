package newbank.server.commands;

import newbank.server.*;

public class ShowStatusCommand extends Command {

  public ShowStatusCommand() {
    super("SHOWSTATUS",
            "<account_name>",
            "show specific account balance and overdraft limit");
  }


  @Override
  public String process(Customer customer, String argument) {

    Account account = customer.getAccount(argument.trim());
    if (account == null) {
      return Constants.FAIL;
    }
    return account.toString();
  }
}
