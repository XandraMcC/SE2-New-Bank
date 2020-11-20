package newbank.server;

import java.util.ArrayList;

public class Account {

	private String accountName;
	private double openingBalance;
	private double currentBalance; // New Double variable to store account's current balance.
	private ArrayList<Account> accounts;
  
	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		this.currentBalance = openingBalance;
	}

	public String toString() {
		return (accountName + ": " + openingBalance);
	}

	public void setAccountName(String name) {
		this.accountName = name;
	}

	public String getAccountName() {
		return accountName;
	}

	/**
	 *  Method to change the current balance. - Useful for transactions etc
	 * @param currentBalance
	 */
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
  
	/**
	 * Method to retrieve the current balance of an account.
	 * @return currentBalance
	 */
	public double getCurrentBalance() {
		return currentBalance;
	}

	public String withdraw(float amount) {
		if (getCurrentBalance() < amount) {
			return "Cannot withdraw £" + amount + " from " + getAccountName() + " not enough funds";
		}
		setCurrentBalance(getCurrentBalance() - amount);
		return "Withdrew £" + amount + " from " + getAccountName();
	}

	public String deposit(float amount) {
		setCurrentBalance(getCurrentBalance() + amount);
		return "Deposited £" + amount + " to account " + getAccountName();
	}
}