package newbank.server.commands;

import newbank.server.Customer;

public abstract class Command {

  private final String identifier;
  private final String form;
  private final String description;

  Command(String identifier, String form, String description) {
    this.identifier = identifier;
    this.form = form;
    this.description = description;
  }

  public String getIdentifier() {
    return identifier;
  }

  public abstract String process(Customer customer, String argument);

  public String toString() {
    String response = identifier;
    if (!description.isBlank()) {
      response += " " + description;
    }
    if (!form.isBlank()) {
      response += " , enter as \"" + identifier + " " + form + "\"";
    }
    return response;
  }

}
