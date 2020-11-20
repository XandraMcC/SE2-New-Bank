package newbank.server.commands;

import newbank.server.*;

public class MakePaymentCommand extends Command {

  public MakePaymentCommand() {
    super("MAKEAPAYMENT",
            "<account> <amount> <payee> <payee_account>",
            "");
  }

  @Override
  public String process(Customer customer, String argument) {
    return null;
  }
}
