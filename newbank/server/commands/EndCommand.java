package newbank.server.commands;

import newbank.server.Customer;

public class EndCommand extends Command {

  public EndCommand() {
    super("END",
            "",
            "exits from a login session and closes the network connection");
  }

  @Override
  public String process(Customer customer, String argument) {
    return null;
  }
}
