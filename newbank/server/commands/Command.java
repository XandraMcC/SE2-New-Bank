package newbank.server.commands;

import newbank.server.Customer;

public abstract class Command {

  private String identifier;
  private String form;
  private String description;

  Command(String identifier, String form, String description) {
    this.identifier = identifier;
    this.form = form;
    this.description = description;
  }

  String getDescription() {
    return description;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getForm() {
    return form;
  }

  public abstract String process(Customer customer, String argument);

}
