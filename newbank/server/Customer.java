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
	public String CurrentBalanceToString(String AccType){ //Parameter A is the chosen account type.
		String NoACC = "Error No Account Found";
		for(Account all : accounts) { //Cycle through each of the customers accounts.
			if (all.getAccountName().equals(AccType)){ // If the chosen account is matched with an actual account balance is returned.
				String currBAL = all.getAccountName() + ": " + all.getCurrentBalance() + "\n";
				return currBAL;
			}
		}
		return NoACC; //If no account is found with that name return error.
	}



	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
