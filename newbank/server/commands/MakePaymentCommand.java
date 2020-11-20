package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;

public class MakePaymentCommand extends Command {

  private final HashMap<String, Customer> customerHashMap;

  public MakePaymentCommand(HashMap<String, Customer> customerHashMap) {
    super("MAKEAPAYMENT",
            "<account> <amount> <payee> <payee_account>",
            "");
    this.customerHashMap = customerHashMap;
  }

  @Override
  public String process(Customer customer, String argument) {

    String[] arguments = argument.split(" ");
    if (arguments.length < 4) {
      return "FAIL";
    }

    Account fromAccount = customer.getAccount(arguments[0]);
    if (fromAccount == null) {
      return "FAIL Your Account Not found!";
    }

    Currency amount;
    try {
      amount = new Currency(arguments[1]);
    } catch (NumberFormatException e) {
      return "FAIL";
    }

    if (!fromAccount.hasFunds(amount)) {
      return "FAIL";
    }

    Customer payee = customerHashMap.get(arguments[2]);
    if (payee == null) {
      return "FAIL Payee Not Found!";
    }

    Account payeeAccount = payee.getAccount(arguments[3]);
    if (payeeAccount == null) {
      return "FAIL Payee Account not found!";
    }

    fromAccount.withdraw(amount);
    payeeAccount.deposit(amount);

    return "Payment " + amount + " made from " + fromAccount.getAccountName() +
            " To " + payee.getName() + " account " + payeeAccount.getAccountName() +
            " your balance " + fromAccount.getBalance();
  }
}
