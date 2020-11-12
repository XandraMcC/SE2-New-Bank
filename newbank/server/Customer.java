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

	public Account getTransactionAccount(String accountName){
		Account account = null;
		for(Account a : accounts) {
			if (accountName.equals(a.getAccountName())){
				account = a;
			}
		}
		return account;
	}

	public void addAccount(Account account) {
		accounts.add(account);		
	}

	public void updateAccount(Account account){
		for(int i =0; i <accounts.size(); i ++) {
			if (account.getAccountName().equals(accounts.get(i).getAccountName())){
				accounts.set(i,account);
			}
		}
	}
}

