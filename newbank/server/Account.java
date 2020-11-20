package newbank.server;

public class Account {

	private final String accountName;
	private Currency currentBalance;
  
	public Account(String accountName, Currency openingBalance) {
		this.accountName = accountName;
		this.currentBalance = openingBalance;
	}

	public String toString() {
		return (accountName + " " + currentBalance);
	}

	public String getAccountName() {
		return accountName;
	}
  
	/**
	 * Method to retrieve the current balance of an account.
	 * @return currentBalance
	 */
	public Currency getBalance() {
		return new Currency(currentBalance);
	}

	/**
	 *  Method to change the current balance. - Useful for transactions etc
	 * @param amount
	 */
	public void setBalance(Currency amount) {
		this.currentBalance = new Currency(amount);
	}

	public boolean hasFunds(Currency amount) {
		return currentBalance.greaterThanOrEqual(amount);
	}

	public void deposit(Currency amount) {
		currentBalance.add(amount);
	}

  public void withdraw(Currency amount) {
		currentBalance.subtract(amount);
  }
}