package newbank.server;

import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	
	public Customer() {
		accounts = new ArrayList<>();
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	//Method to get current balance of specific account type.
	public String CurrentBalanceToString(String A){ //Parameter A is the chosen account type.
		String x = "Error No Account Found"; // Still Needs work.
		String s = null;
		for(Account a : accounts) { //Cycle through each of the customers accounts.
			if (a.getAccountName().equals(A)){ // If the chosen account is matched with an actual account balance is returned.
				s = a.getAccountName() + ": " + a.getCurrentBalance() + "\n";
				return s;
			}
		}
		return s;
	}



	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
