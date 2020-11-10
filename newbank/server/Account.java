package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	//New double for current balance

	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
	}
	
	public String toString() {
		return (accountName + ": " + openingBalance);
	}
	// add in print for current balance
}
