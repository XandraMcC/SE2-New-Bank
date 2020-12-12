package newbank.server.commands;

import newbank.server.*;

import java.util.HashMap;

public class MakePaymentCommand extends Command {

  private final HashMap<String, Customer> customerHashMap;

  public MakePaymentCommand(HashMap<String, Customer> customerHashMap) {
    super("MAKEPAYMENT",
            "<account_name> <amount> <payee_name> <payee_account>",
            "makes a payment from your account to another account");
    this.customerHashMap = customerHashMap;
  }

  @Override
  public String process(Customer customer, String argument) {
    String[] arguments = argument.split(" ");

    if (arguments.length < 4) {
      return Constants.FAILNOTENOUGHARGS;
    }

    Account fromAccount = customer.getAccount(arguments[0]);
    if (fromAccount == null) {
      return Constants.FAILACCOUNTNOTFOUND;
    }

    Customer payee = customerHashMap.get(arguments[2]);
    if (payee == null) {
      return Constants.FAILPAYEENOTFOUND;
    }

    Account payeeAccount = payee.getAccount(arguments[3]);
    if (payeeAccount == null) {
      return Constants.FAILPAYEEACCOUNTNOTFOUND;
    }

    Currency amount;
    try {
      amount = Currency.FromString(arguments[1]);
    } catch (NumberFormatException e) {
      return Constants.FAILPAYMENTNOTFOUND;
    }

    try {
      fromAccount.withdraw(amount);
    } catch (InsufficientFundsException e) {
      return Constants.FAILINSUFFICIENTFUNDS;
    }

    payeeAccount.deposit(amount);

    return "Payment " + amount.toString() + " made from " + fromAccount.getAccountName() +
            " to " + payee.getName() + " account " + payeeAccount.getAccountName() +
            ". Your new balance is " + fromAccount.getBalance().toString();
  }
}
