package newbank.server;

import java.util.ArrayList;

public class Customer {

	private ArrayList<Account> accounts;

	public Customer() {
		accounts = new ArrayList<>();
	}

	public String accountsToString() {
		String s = "";
		for (Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	//Method to allow account to be transacted upon to be accessed
	public Account getTransactionAccount(String accountName) {
		Account account = null;
		for (Account a : accounts) {
			if (accountName.equals(a.getAccountName())) {
				account = a;
			}
		}
		return account;
	}


	//method to return the current balance
	public String currentBalance(String accountType) {
		String s = null;
		for (Account a : accounts) {
			if (a.getAccountName().equals(accountType)) {
				s = a.getAccountName() + ": " + a.getCurrentBalance();
				return s;
			}
		}
		return s;
	}



	public void addAccount(Account account) {
		accounts.add(account);		
	}

	//Method to allow access to update account balances
	public void updateAccount(Account account){
		for(int i =0; i <accounts.size(); i ++) {
			if (account.getAccountName().equals(accounts.get(i).getAccountName())){
				accounts.set(i,account);
			}
		}
	}
}

