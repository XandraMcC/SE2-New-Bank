package newbank.server;

import newbank.server.commands.OfferLoanMarketCommand;

public class LoanOffer {

  private Currency amount; // The amount of money being offered
  private float interestRate; // The rate at which this is being offered APR e.g. 5% would be 0.05
  private Customer lender; // The person offering the loan
  private String accountName; // The account the loan is being

  /**
   * Constructor for a LoanOffer, a micro loan offer is an offer by a user loan money at an interest rate
   * @param lender the customer offering to loan the money
   * @param accountName the account from whcih the loan funds are paid
   * @param amount the maximum amount that is being offered in loans
   * @param interestRate the rate at which the loan is offered
   */
  public LoanOffer(Customer lender, String accountName, Currency amount, float interestRate) {
    this.lender = lender;
    this.accountName = accountName;
    this.amount = new Currency(amount);
    this.interestRate = interestRate;
  }




  /**
   * Takes out a new loan from the offer
   * @param receivingAccount the account to receive the funds
   * @param loanName the name of the loan
   * @param loanAmount the amount to be loaned
   * @return the new loan
   * @throws Exception if the amount exceeds the maximum offered or the loan lender does not have enough funds
   */
  public Loan takeLoan(Account receivingAccount, String loanName, Currency loanAmount) throws Exception {
    if (amount.getValue() < loanAmount.getValue()) {
      throw new Exception("Loan requested exceeds loan amount on offer");
    }
    if (!lender.getAccount(accountName).hasFunds(loanAmount)) {
      throw new Exception("Loan lender does not have enough funds in their account!");
    }
    amount.subtract(loanAmount); // Reduce the amount offered in the loan market
    Loan newLoan = new Loan(loanName, loanAmount, interestRate);
    lender.getAccount(accountName).withdraw(loanAmount);
    receivingAccount.deposit(loanAmount);
    return newLoan;
  }

  public Customer getLender() {
    return lender;
  }

  public Currency getAmount() {
    return amount;
  }

  public float getInterestRate() {
    return interestRate;
  }
}
