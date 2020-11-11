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
	public String AllCurrentBalanceToString(){
		String s = "";
		for(Account a : accounts){
			s += a.getAccountName() + ": " + String.valueOf(a.getCurrentBalance()) + "\n";
		}
		return s;
	}

	public String CurrentBalanceToString(String A){
		String x = "Error No Account Found";
		String s = null;
		for(Account a : accounts) {
			if (a.getAccountName() == A){
				s = a.getAccountName() + ": " + String.valueOf(a.getCurrentBalance()) + "\n";
				return s;
			}
			else{return x;}
		}
		return s;
	}



	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
