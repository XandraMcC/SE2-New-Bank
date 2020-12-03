package newbank.server.commands;

import newbank.server.Account;
import newbank.server.Customer;

public class ShowTransactionHistory extends Command {

  public ShowTransactionHistory() {
    super("SHOWTRANSACTIONHISTORY",
            "<account_name>",
            "Retrieves the balance for specific account");
  }

  @Override
  public String process(Customer customer, String argument) {

    Account account = customer.getAccount(argument);
    if (account == null) {
      return "FAIL";
    }

    return account.getTransactionHistory();
  }
}
