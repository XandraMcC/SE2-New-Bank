package newbank.server.commands;

import newbank.server.Customer;

// Abstracts the common behaviour and data of all commands this is intended
// to be extended by command classes which implement each command.
public abstract class Command {

  private final String identifier; // the word which invokes this command e.g. SHOWMYACCOUNTS
  private final String form; // description of the parameters this command accepts
  private final String description; // what the command does

  /**
   * Constructor
   *
   * @param identifier the word which invokes this command e.g. SHOWMYACCOUNTS
   * @param form the arguments which are provided with the command
   * @param description what the command does
   */
  Command(String identifier, String form, String description) {
    this.identifier = identifier;
    this.form = form;
    this.description = description;
  }

  /**
   * @return the word which invokes this command e.g. SHOWMYACCOUNTS
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   * @param customer the customer performing this command
   * @param argument the argument to be processed by the command
   * @return a string stating the result of the operation for display to the user
   *         if the operation failed then this should start with 'FAIL' followed
   *         by a helpful description of the failure
   */
  public abstract String process(Customer customer, String argument);

  /**
   * Pretty prints the command in a form suitable for display to a user
   * if a description or form is set for the command then this is included
   *
   * @return pretty printed command
   */
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
