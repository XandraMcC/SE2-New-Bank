package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	private double currentBalance; // New Double variable to store account's current balance.


	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
	}
	
	public String toString() {
		return (accountName + ": " + openingBalance);
	}



}
