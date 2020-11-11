package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	private double currentBalance; // New Double variable to store account's current balance.


	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		openingBalance = currentBalance;
	}
	
	public String toString() {
		return (accountName + ": " + openingBalance);
	}

	//Method to retrieve the current balance of an account.
	public double getCurrentBalance() {
		return currentBalance;
	}

	//Method to change the current balance. - Useful for transactions etc.
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

}
