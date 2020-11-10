package newbank.server;

import java.util.ArrayList;

public class Customer {

	private String name;
	private String password;
	private ArrayList<Account> accounts;
	
	public Customer(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new ArrayList<>();
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	public void addAccount(Account account) {
		accounts.add(account);		
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
