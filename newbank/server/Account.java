package newbank.server;

public class Account {

	private String accountName;
	private double openingBalance;
	private double currentBalance; // New Double variable to store account's current balance.
  
	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		this.currentBalance = openingBalance;
	}

	public String toString() {
		return (accountName + ": " + openingBalance);
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
	 * @param newcurrbal
	 */
	public void setBalance(double newcurrbal) {
		this.currentBalance = newcurrbal;
	}

	public void deposit(Currency ammount) {

	}

}