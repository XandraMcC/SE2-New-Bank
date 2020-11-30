package newbank.server.commands;

import newbank.server.Customer;

public class EndCommand extends Command {

  public EndCommand() {
    super("END",
            "",
            "Exits from a login session and close network connection");
  }

  @Override
  public String process(Customer customer, String argument) {
    return null;
  }
}
