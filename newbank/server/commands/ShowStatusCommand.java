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

    String[] arguments = argument.split(" ");
    if (arguments.length < 1) {
      return "FAIL";
    }

    String accountName = arguments[0];

    Account account = customer.getTransactionAccount(accountName);
    if (account == null) {
      return "FAIL";
    }
    return account.toString();
  }
}
