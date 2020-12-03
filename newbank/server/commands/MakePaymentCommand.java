package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;

public class MakePaymentCommand extends Command {

  private final HashMap<String, Customer> customerHashMap;

  public MakePaymentCommand(HashMap<String, Customer> customerHashMap) {
    super("MAKEPAYMENT",
            "<account_name> <amount> <payee_name> <payee_account>",
            "");
    this.customerHashMap = customerHashMap;
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 4) {
      return "FAIL - Not enough arguments entered.";
    }

    Account fromAccount = customer.getAccount(arguments[0]);
    if (fromAccount == null) {
      return "FAIL - Your account is not found.";
    }

    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL - payment amount not recognised.";
    }

    Customer payee = customerHashMap.get(arguments[2]);
    if (payee == null) {
      return "FAIL - Payee not found.";
    }

    Account payeeAccount = payee.getAccount(arguments[3]);
    if (payeeAccount == null) {
      return "FAIL - Payee account not found.";
    }

    try {
      fromAccount.withdraw(amount);
    } catch (InsufficientFundsException e) {
      return "FAIL - Insufficient funds available to make payment.";
    }
    payeeAccount.deposit(amount);

    return "Payment " + amount.toString() + " made from " + fromAccount.getAccountName() +
            " to " + payee.getName() + " account " + payeeAccount.getAccountName() +
            ". Your new balance is " + fromAccount.getBalance().toString();
  }
}
