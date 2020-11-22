package newbank.server.commands;

import newbank.server.*;

public class ShowStatusCommand extends Command {

  public ShowStatusCommand() {
    super("SHOWSTATUS",
            "<account_name>",
            "");
  }


  @Override
  public String process(Customer customer, String argument) {

    Account account = customer.getAccount(argument.trim());
    if (account == null) {
      return "FAIL";
    }
    return account.toString();
  }
}
