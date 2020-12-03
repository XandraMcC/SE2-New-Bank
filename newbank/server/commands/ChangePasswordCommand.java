package newbank.server.commands;

import newbank.server.*;

public class ChangePasswordCommand extends Command {

  public ChangePasswordCommand() {
    super("CHANGEPASSWORD",
            "<new_password>",
            "");
  }

  @Override
  public String process(Customer customer, String argument) {
    try {
      customer.updatePassword(argument);
    } catch (Exception e) {
      return "FAIL - password not updated";
    }
    return "Password updated";
  }
}
