package newbank.server;

public class Loan {

  private Currency initialAmount;
  private Currency balance;
  private float interestRate;

  /**
   * Loan constructor
   * @param name of the loan
   * @param amount initial amount borrowed
   * @param interestRate the rate of interest this was borrowed at
   */
  public Loan(String name, Currency amount, float interestRate) {
    this.initialAmount = new Currency(amount);
    this.balance = new Currency(amount);
    this.interestRate = interestRate;
  }

  /**
   * Adds 1 days interest onto the loan
   */
  public void incrementDailyInterest() {
    int newBalance = (int) (balance.getValue() * (interestRate + 1.0) / 365.0);
    balance.setValue(newBalance);
  }

  /**
   * @return the loan amount outstanding
   */
  public Currency getBalance() {
    return new Currency(balance);
  }

  /**
   * Makes a payment towards the loan
   * @param amount to pay
   */
  public void makePayment(Currency amount) {
    balance.subtract(amount);
  }
}
